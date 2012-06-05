package garjust.jag2d.collision;

import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.ReadablePoint;

import java.awt.Color;

public class BoundingCircle implements BoundingGeometry {

    private final Point center;
    private final float radius;

    public BoundingCircle(final ReadablePoint center, final float radius) {
        this.center = center().copy();
        this.radius = radius;
    }

    public Point center() {
        return center.copy();
    }

    public int snappedRadius() {
        return Math.round(radius);
    }

    public void draw(final java.awt.Graphics2D graphics) {
        final Color color_save = graphics.getColor();
        graphics.setColor(Color.GREEN);
        graphics.drawOval(center.snappedX(), center.snappedY(), snappedRadius(), snappedRadius());
        graphics.setColor(color_save);
    }
}
