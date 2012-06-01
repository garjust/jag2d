package garjust.jag2d.collision;

import garjust.jag2d.geometry.circle.Circle;
import garjust.jag2d.geometry.circle.ReadableCircle;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.geometry.polygon.ReadablePolygon;
import garjust.jag2d.geometry.rectangle.ReadableRectangle;
import garjust.jag2d.geometry.vector.ReadableVector;

import java.awt.Color;

public class BoundingCircle implements BoundingGeometry, ReadableCircle {

    private final Point center;
    private final float radius;

    public BoundingCircle(final ReadablePoint center, final float radius) {
        this.center = new Point(center);
        this.radius = radius;
    }

    public BoundingCircle(final Circle circle) {
        this.center = circle.center();
        this.radius = circle.radius();
    }

    // TODO rounding makes small circles error prone
    public BoundingCircle(final ReadableRectangle rectangle) {
        this.center = new Point(rectangle.x() + rectangle.w() / 2, rectangle.y() + rectangle.h() / 2);
        this.radius = (int) Math.sqrt(rectangle.w() + rectangle.h());
    }

    // TODO rounding makes small circles error prone
    public BoundingCircle(final ReadablePolygon polygon) {
        this.center = polygon.centre();
        float max = 0;
        for (ReadablePoint vertex : polygon.vertices()) {
            final ReadableVector vector = Point.pointToPointVector(center, vertex);
            final float length = vector.length();
            if (length > max) {
                max = length;
            }
        }
        this.radius = (int) max;
    }

    /**
     * Draws the bounding box [TESTING PURPOSES]
     * 
     * @param graphics
     *            Graphics context for drawing
     */
    public void draw(final java.awt.Graphics2D graphics) {
        final Color color_save = graphics.getColor();
        graphics.setColor(Color.GREEN);
        graphics.drawOval(center.snappedX(), center.snappedY(), snappedRadius(), snappedRadius());
        graphics.setColor(color_save);
    }

    /**
     * Builds a rectangle from the bounding box
     * 
     * @return A rectangle equal to this bounding box
     */
    public Circle toCircle() {
        return new Circle(center, radius);
    }

    /**
     * Builds a string representing an Axis-Aligned Bounding Circle in the form<br />
     * [AABB: $UL $LR]
     * 
     * @return String representation of a BoundingCircle
     */
    @Override
    public String toString() {
        return "[BC: pos=" + center.toString() + " radius=" + radius + "]";
    }

    @Override
    public Point center() {
        return new Point(center);
    }

    @Override
    public float radius() {
        return radius;
    }

    @Override
    public int snappedRadius() {
        return Math.round(radius);
    }
}
