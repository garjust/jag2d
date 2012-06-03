package garjust.jag2d.geometry.circle;

import garjust.jag2d.collision.BoundingBox;
import garjust.jag2d.collision.Collidable;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.PointList;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.geometry.polygon.Polygon;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class Circle implements Collidable, MoveableCircle, ReadableCircle {

    private Point center;
    private float radius;

    public Circle(final Point center, final float radius) {
        this.center = center.copy();
        this.radius = radius;
    }

    public final Polygon poly() {
        return poly(6);
    }

    public final Polygon poly(final int points) {
        final PointList vertices = new PointList();
        final float point_theta = ((float) Math.PI * 2) / points;
        vertices.add(new Point(center.x(), center.y() + radius));
        for (int i = 1; i < points; i++) {
            final Point rotatedVertex = vertices.get(i - 1).copy();
            rotatedVertex.rotate(point_theta, center);
            vertices.add(rotatedVertex);
        }
        return new Polygon(vertices);
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(this);
    }

    @Override
    public void draw(final java.awt.Graphics2D graphics) {
        graphics.drawOval(center.snappedX(), center.snappedY(), snappedRadius() * 2, snappedRadius() * 2);
    }

    @Override
    public void rotate(float theta) {
        center.rotate(theta);
    }

    @Override
    public void rotate(float theta, ReadablePoint center) {
        this.center.rotate(theta, center);
    }

    @Override
    public void scale(float scalar) {
        radius *= scalar;
    }

    @Override
    public void translate(float x, float y) {
        center.translate(x, y);
    }

    @Override
    public int snappedRadius() {
        return Math.round(radius);
    }

    @Override
    public Circle copy() {
        return new Circle(center, radius);
    }
}
