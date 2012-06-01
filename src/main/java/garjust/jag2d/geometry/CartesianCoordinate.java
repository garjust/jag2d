package garjust.jag2d.geometry;

import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.util.FloatMath;
import garjust.jag2d.util.GraphicsConfig;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public abstract class CartesianCoordinate<C extends CartesianCoordinate<?>> implements Drawable {

    protected float x;
    protected float y;

    public int snappedX() {
        return Math.round(x);
    }

    public int snappedY() {
        return Math.round(y);
    }

    public C rotate(final float theta) {
        final float temp_x = x;
        x = x * FloatMath.cos(theta) - y * FloatMath.sin(theta);
        y = temp_x * FloatMath.sin(theta) + y * FloatMath.cos(theta);
        return (C) this;
    }

    public C rotate(final float theta, final ReadablePoint center) {
        Point centered = new Point(x - center.x(), y - center.y());
        x = (centered.x() * FloatMath.cos(theta) - centered.y() * FloatMath.sin(theta)) + center.x();
        y = (centered.x() * FloatMath.sin(theta) + centered.y() * FloatMath.cos(theta)) + center.y();
        return (C) this;
    }

    public C scale(final float scalar) {
        x *= scalar;
        y *= scalar;
        return (C) this;
    }

    public C translate(final float x, final float y) {
        this.x += x;
        this.y += y;
        return (C) this;
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

    public float[] toArray() {
        final float[] point = { x, y };
        return point;
    }

    public abstract C copy();
}
