package garjust.jag2d.geometry.point;

import garjust.jag2d.geometry.Drawable;
import garjust.jag2d.geometry.Geometry;
import garjust.jag2d.geometry.vector.Vector;
import garjust.jag2d.util.FloatMath;
import garjust.jag2d.util.GraphicsConfig;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public final class Point implements Drawable, Geometry, ReadablePoint, MoveablePoint {

    public static final ReadablePoint ZERO = new Point(0, 0);
    //
    private float x;
    private float y;

    public Point() {
        this.x = ZERO.x();
        this.y = ZERO.y();
    }

    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Point(final ReadablePoint point) {
        x = point.x();
        y = point.y();
    }

    @Override
    public int getSnappedX() {
        return Math.round(x);
    }

    @Override
    public int getSnappedY() {
        return Math.round(y);
    }

    /**
     * Rotates the point about 0
     * 
     * @param theta
     *            Rotation angle
     * @return This
     */
    @Override
    public Point rotate(final float theta) {
        final float temp_x = x;
        x = x * FloatMath.cos(theta) - y * FloatMath.sin(theta);
        y = temp_x * FloatMath.sin(theta) + y * FloatMath.cos(theta);
        return this;
    }

    /**
     * Rotates the point about the point center
     * 
     * @param theta
     *            Rotation angle
     * @param center
     *            Rotation point
     * @return This
     */
    @Override
    public Point rotate(final float theta, final ReadablePoint center) {
        Point centered = new Point(x - center.x(), y - center.y());
        x = (centered.x() * FloatMath.cos(theta) - centered.y() * FloatMath.sin(theta)) + center.x();
        y = (centered.x() * FloatMath.sin(theta) + centered.y() * FloatMath.cos(theta)) + center.y();
        return this;
    }

    @Override
    public Point scale(final float scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    /**
     * Translates the point by x,y units
     * 
     * @param x
     *            X units to translate
     * @param y
     *            Y units to translate
     * @return This
     */
    @Override
    public Point translate(final float x, final float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Returns a new point with rounded coordinates
     * 
     * @return Rounded point
     */
    public Point snap() {
        return new Point(Math.round(x), Math.round(y));
    }

    public static Vector pointToPointVector(final ReadablePoint point1, final ReadablePoint point2) {
        return new Vector(point2.x() - point1.x(), point2.y() - point1.y());
    }

    /**
     * 
     * @param graphics
     */
    @Override
    public void draw(final Graphics2D graphics) {
        this.draw(graphics, java.awt.Color.RED);
    }

    @Override
    public void draw(final Graphics2D graphics, final Color colour) {
        final GraphicsConfig graphics_config = new GraphicsConfig(graphics);
        graphics.setColor(colour);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawRect(getSnappedX(), getSnappedY(), 1, 1);
        graphics_config.set(graphics);
    }

    public float[] toArray() {
        final float[] point = { x, y };
        return point;
    }

    public Vector toVector() {
        return new Vector(x, y);
    }
}
