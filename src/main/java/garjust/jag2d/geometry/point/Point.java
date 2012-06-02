package garjust.jag2d.geometry.point;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.CartesianCoordinate;
import garjust.jag2d.geometry.vector.Vector;
import garjust.jag2d.util.FloatMath;
import garjust.jag2d.util.GraphicsConfig;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Point extends CartesianCoordinate implements Drawable, ReadablePoint, MoveablePoint {

    public static final ReadablePoint ZERO = new Point(0, 0);

    public Point(final float x, final float y) {
        super(x, y);
    }

    public Point snap() {
        return new Point(snappedX(), snappedY());
    }

    public static Vector pointToPointVector(final ReadablePoint point1, final ReadablePoint point2) {
        return new Vector(point2.x() - point1.x(), point2.y() - point1.y());
    }

    @Override
    public Point rotate(final float theta) {
        super.rotate(theta);
        return this;
    }

    @Override
    public Point rotate(final float theta, final ReadablePoint center) {
        Point centered = new Point(x - center.x(), y - center.y());
        x = (centered.x() * FloatMath.cos(theta) - centered.y() * FloatMath.sin(theta)) + center.x();
        y = (centered.x() * FloatMath.sin(theta) + centered.y() * FloatMath.cos(theta)) + center.y();
        return this;
    }

    @Override
    public Point scale(final float scalar) {
        super.scale(scalar);
        return this;
    }

    @Override
    public Point translate(final float x, final float y) {
        super.translate(x, y);
        return this;
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
