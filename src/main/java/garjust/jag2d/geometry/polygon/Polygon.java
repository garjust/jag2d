package garjust.jag2d.geometry.polygon;

import garjust.jag2d.collision.BoundingBox;
import garjust.jag2d.collision.Collidable;
import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.CenterableGeometry;
import garjust.jag2d.geometry.point.MoveablePoint;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.PointList;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.geometry.rectangle.ReadableRectangle;
import garjust.jag2d.util.Sort;

import java.awt.Color;
import java.awt.Graphics2D;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class Polygon implements Collidable, Drawable, CenterableGeometry {

    private PointList vertices;
    @Setter(AccessLevel.NONE)
    private Point centre;
    @Setter(AccessLevel.NONE)
    private Polygon hull;

    public Polygon() {
        this(new PointList());
    }

    public Polygon(final ReadableRectangle rectangle) {
        this.vertices = new PointList(4);
        vertices.add(new Point(rectangle.origin().x(), rectangle.origin().y()));
        vertices.add(new Point(rectangle.origin().x(), rectangle.origin().y() + rectangle.h()));
        vertices.add(new Point(rectangle.origin().x() + rectangle.w(), rectangle.origin().y()));
        vertices.add(new Point(rectangle.origin().x() + rectangle.w(), rectangle.origin().y() + rectangle.h()));
        this.centre = null;
        this.hull = null;
    }

    public Polygon(final PointList vertices) {
        this.vertices = new PointList(vertices);
        this.centre = null;
        this.hull = null;
    }

    public final Point centre() {
        if (centre == null) {
            float x_total = 0;
            float y_total = 0;
            for (Point vertex : this.vertices()) {
                x_total += vertex.x();
                y_total += vertex.y();
            }
            x_total /= this.vertices().size();
            y_total /= this.vertices().size();
            centre = new Point(x_total, y_total);
        }
        return centre.copy();
    }

    public final Polygon hull() {
        if (hull == null) {
            Point lowest_y = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
            for (Point vertex : vertices) {
                if (vertex.y() < lowest_y.y()) {
                    lowest_y = vertex;
                } else if (vertex.y() == lowest_y.y()) {
                    if (vertex.x() < lowest_y.x()) {
                        lowest_y = vertex;
                    }
                }
            }
            Point[] points = new Point[vertices.size()];
            points = vertices.toArray(points);
            float[] sortee = new float[vertices.size()];
            for (int i = 0; i < sortee.length; i++) {
                if (points[i] == lowest_y) {
                    sortee[i] = 0;
                } else {
                    sortee[i] = points[i].pointToPointVector(lowest_y).angle();
                }
            }
            Sort.bubble(sortee, points);
            PointList the_hull = new PointList(points.length);
            the_hull.add(points[0]);
            the_hull.add(points[1]);
            int stack = 2;
            for (int i = 2; i < points.length; i++) {
                while (ccw(the_hull.get(stack - 2), the_hull.get(stack - 1), points[i]) > 0) { // WHILE
                                                                                               // RIGHT
                                                                                               // TURN
                    the_hull.remove(stack - 1);
                    stack--;
                    if (stack == 1) {
                        break;
                    }
                }
                the_hull.add(points[i]);
                stack++;
            }
            hull = new Polygon(the_hull);
        }
        return hull;
    }

    @Override
    public void rotate(final float theta) {
        for (MoveablePoint vertex : vertices) {
            vertex.rotate(theta);
        }
        if (hull != null) {
            hull.rotate(theta);
        }
        if (centre != null) {
            centre.rotate(theta);
        }
    }

    @Override
    public void rotate(final float theta, final ReadablePoint position) {
        for (MoveablePoint vertex : vertices) {
            vertex.rotate(theta, position);
        }
        if (hull != null) {
            hull.rotate(theta, position);
        }
        if (centre != null) {
            centre.rotate(theta, position);
        }
    }

    @Override
    public void scale(final float scalar) {
        centre();
        for (MoveablePoint vertex : vertices) {
            vertex.translate(-1 * centre.x(), -1 * centre.y());
            vertex.scale(scalar);
            vertex.translate(centre.x(), centre.y());
        }
        resetCentre();
        resetHull();
    }

    @Override
    public void translate(final float x, final float y) {
        for (MoveablePoint vertex : vertices) {
            vertex.translate(x, y);
        }
        if (hull != null) {
            hull.translate(x, y);
        }
        if (centre != null) {
            centre.translate(x, y);
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        draw(graphics, false);
    }

    public void draw(final Graphics2D graphics, final boolean debug) {
        if (debug) {
            centre();
            hull();
            Color color_save = graphics.getColor();
            graphics.setColor(Color.YELLOW);
            final int[][] coordinate_matrix = hull().vertices().getCoordinateMatrix();
            graphics.drawPolygon(coordinate_matrix[0], coordinate_matrix[1], hull().vertices().size());
            centre.draw(graphics, Color.YELLOW);
            graphics.setColor(color_save);
        }
        final int[][] coordinate_matrix = vertices.getCoordinateMatrix();
        graphics.drawPolygon(coordinate_matrix[0], coordinate_matrix[1], vertices.size());
    }

    /**
     * 
     * @return
     */
    @Override
    public BoundingBox bound() {
        return new BoundingBox(this);
    }

    public Polygon deform(final Point deform_origin) {
        return deform(deform_origin, 1, 1);
    }

    public Polygon deform(final Point deform_origin, final float radial_variance, final float tangential_variance) {
        float radial_magnitude = 0;
        for (int i = 0; i < vertices.size(); i++) {
            radial_magnitude = (float) Math.random() * radial_variance + 1;
            deformPoint(vertices.get(i), deform_origin, radial_magnitude);
            deformPoint(vertices.get(i == 0 ? vertices.size() - 1 : i - 1), deform_origin, radial_magnitude / 2);
            deformPoint(vertices.get((i + 1) % vertices.size()), deform_origin, radial_magnitude / 2);
        }
        return this;
    }

    private void deformPoint(final Point point, final Point deform_origin, final float radial_magnitude) {
        point.translate(-1 * deform_origin.x(), -1 * deform_origin.y());
        point.scale(radial_magnitude);
        point.translate(deform_origin.x(), deform_origin.y());
    }

    private static float ccw(final Point p1, final Point p2, final Point p3) {
        return (p2.x() - p1.x()) * (p3.y() - p1.y()) - (p2.y() - p1.y()) * (p3.x() - p1.x());
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (this.getClass() != other.getClass()) {
            return false;
        }
        final ReadablePolygon polygon = (Polygon) other;
        if (vertices.equals(polygon.vertices())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.vertices != null ? this.vertices.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        String polygon = "[Polygon:";
        for (ReadablePoint vertex : vertices) {
            polygon += " " + vertex.toString();
        }
        return polygon + "]";
    }

    @Override
    public Polygon copy() {
        return new Polygon(vertices.copy(), centre.copy());
    }
}
