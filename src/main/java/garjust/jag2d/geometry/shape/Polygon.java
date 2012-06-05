package garjust.jag2d.geometry.shape;

import garjust.jag2d.collision.BoundingBox;
import garjust.jag2d.collision.Collidable;
import garjust.jag2d.geometry.CenterableGeometry;
import garjust.jag2d.geometry.Copyable;
import garjust.jag2d.geometry.point.MoveablePoint;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.PointList;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.util.Sort;

import java.awt.Color;
import java.awt.Graphics2D;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class Polygon implements Collidable, CenterableGeometry, Copyable<Polygon> {

    private PointList vertices;
    @Setter(AccessLevel.NONE)
    private Point center;
    @Setter(AccessLevel.NONE)
    private Polygon hull;

    public Polygon(final PointList vertices) {
        this.vertices = vertices;
        this.center = null;
        this.hull = null;
    }

    public Polygon(final Rectangle rectangle) {
        this.vertices = new PointList(4);
        vertices.add(rectangle.ul().copy());
        vertices.add(new Point(rectangle.lr().x(), rectangle.ul().y()));
        vertices.add(rectangle.lr().copy());
        vertices.add(new Point(rectangle.ul().x(), rectangle.lr().y()));
        this.center = null;
        this.hull = copy();
    }

    public final Point center() {
        if (center == null) {
            center = findCenter();
        }
        return center.copy();
    }

    public final Polygon hull() {
        if (hull == null) {
            hull = findHull();
        }
        return hull;
    }

    private Point findCenter() {
        float x_total = 0;
        float y_total = 0;
        for (Point vertex : this.vertices()) {
            x_total += vertex.x();
            y_total += vertex.y();
        }
        x_total /= this.vertices().size();
        y_total /= this.vertices().size();
        return new Point(x_total, y_total);
    }

    private Polygon findHull() {
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
        return new Polygon(the_hull);
    }

    @Override
    public void rotate(final float theta) {
        for (MoveablePoint vertex : vertices) {
            vertex.rotate(theta);
        }
        if (hull != null) {
            hull.rotate(theta);
        }
        if (center != null) {
            center.rotate(theta);
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
        if (center != null) {
            center.rotate(theta, position);
        }
    }

    @Override
    public void scale(final float scalar) {
        center();
        for (MoveablePoint vertex : vertices) {
            vertex.translate(-1 * center.x(), -1 * center.y());
            vertex.scale(scalar);
            vertex.translate(center.x(), center.y());
        }
        center = null;
        hull = null;
    }

    @Override
    public void translate(final float x, final float y) {
        for (MoveablePoint vertex : vertices) {
            vertex.translate(x, y);
        }
        if (hull != null) {
            hull.translate(x, y);
        }
        if (center != null) {
            center.translate(x, y);
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        draw(graphics, false);
    }

    public void draw(final Graphics2D graphics, final boolean debug) {
        if (debug) {
            center();
            hull();
            Color color_save = graphics.getColor();
            graphics.setColor(Color.YELLOW);
            hull.draw(graphics);
            center.draw(graphics, Color.YELLOW);
            graphics.setColor(color_save);
        }
        vertices.drawConnected(graphics);
    }

    @Override
    public BoundingBox bound() {
        final Point ul = new Point(Integer.MAX_VALUE, Integer.MIN_VALUE);
        final Point lr = new Point(Integer.MIN_VALUE, Integer.MAX_VALUE);
        for (final ReadablePoint vertex : vertices) {
            if (vertex.x() < ul.x()) {
                ul.x(vertex.x());
            }
            if (vertex.x() > lr.x()) {
                lr.x(vertex.x());
            }
            if (vertex.y() < lr.y()) {
                lr.y(vertex.y());
            }
            if (vertex.y() > ul.y()) {
                ul.y(vertex.y());
            }
        }
        return new BoundingBox(ul, lr);
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
    public Polygon copy() {
        return new Polygon(vertices.copy());
    }
}
