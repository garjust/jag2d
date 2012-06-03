package garjust.jag2d.geometry.point;

import garjust.jag2d.geometry.CartesianCoordinate;
import garjust.jag2d.geometry.vector.Vector;
import garjust.jag2d.util.FloatMath;
import garjust.jag2d.util.GraphicsConfig;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Point extends CartesianCoordinate implements ReadablePoint, MoveablePoint {

    public static final ReadablePoint ZERO = new Point(0, 0);

    public Point(final float x, final float y) {
        super(x, y);
    }

    @Override
    public Point returnSnapped() {
        return new Point(snappedX(), snappedY());
    }

    public Vector pointToPointVector(final ReadablePoint otherPoint) {
        return new Vector(otherPoint.x() - x, otherPoint.y() - y);
    }

    @Override
    public void rotate(final float theta, final ReadablePoint center) {
        Point centered = new Point(x - center.x(), y - center.y());
        x = (centered.x() * FloatMath.cos(theta) - centered.y() * FloatMath.sin(theta)) + center.x();
        y = (centered.x() * FloatMath.sin(theta) + centered.y() * FloatMath.cos(theta)) + center.y();
    }

    @Override
    public void draw(final Graphics2D graphics) {
        this.draw(graphics, java.awt.Color.RED);
    }

    public void draw(final Graphics2D graphics, final Color colour) {
        final GraphicsConfig graphics_config = new GraphicsConfig(graphics);
        graphics.setColor(colour);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawRect(snappedX(), snappedY(), 1, 1);
        graphics_config.set(graphics);
    }

    @Override
    public Point copy() {
        return new Point(x, y);
    }
}
