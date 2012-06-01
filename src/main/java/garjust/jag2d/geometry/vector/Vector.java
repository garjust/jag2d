package garjust.jag2d.geometry.vector;

import garjust.jag2d.geometry.Drawable;
import garjust.jag2d.geometry.Geometry;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.util.FloatMath;
import garjust.jag2d.util.GraphicsConfig;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class Vector implements Drawable, Geometry, ReadableVector, MoveableVector {

    public static final ReadableVector ZERO = new Vector(0, 0);
    public static final ReadableVector X_UNIT_VECTOR = new Vector(1, 0);
    public static final ReadableVector Y_UNIT_VECTOR = new Vector(0, 1);
    public static final ReadableVector DIAGONAL_VECTOR = new Vector(1, 1).unit();
    private float x;
    private float y;

    public Vector() {
        this.x = ZERO.x();
        this.y = ZERO.y();
    }

    public Vector(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Vector(final ReadableVector vector) {
        x = vector.x();
        y = vector.y();
    }

    @Override
    public int snappedX() {
        return Math.round(x);
    }

    @Override
    public int snappedY() {
        return Math.round(y);
    }

    @Override
    public float length() {
        return FloatMath.sqrt(x * x + y * y);
    }

    @Override
    public float angle() {
        return length() == 0 ? 0 : FloatMath.acos(x / length());
    }

    @Override
    public Vector rotate(final float theta) {
        final float temp_x = x;
        x = x * FloatMath.cos(theta) - y * FloatMath.sin(theta);
        y = temp_x * FloatMath.sin(theta) + y * FloatMath.cos(theta);
        return this;
    }

    @Override
    public Vector scale(final float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    /**
     * Translates the vector by x,y units
     * 
     * <p>
     * DESTRUCTIVE
     * </p>
     * 
     * @param x
     * @param y
     * @return This vector
     */
    @Override
    public Vector translate(final float x, final float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector length(final float new_length) {
        final ReadableVector unit = unit();
        return new Vector(unit.x() * new_length, unit.y() * new_length);
    }

    public Vector snap() {
        return new Vector(Math.round(x), Math.round(y));
    }

    public Vector negate() {
        return new Vector(-x, -y);
    }

    public Vector unit() {
        final float magnitude = length();
        return new Vector(x / magnitude, y / magnitude);
    }

    public Vector normal() {
        return new Vector(-y, x);
    }

    public static float angle(Vector vector1, Vector vector2) {
        return FloatMath.acos(dot(vector1, vector2) / (vector1.length() * vector2.length())) * 180 / FloatMath.PI;
    }

    public static float dot(final ReadableVector vector1, final ReadableVector vector2) {
        return vector1.x() * vector2.x() + vector1.y() * vector2.y();
    }

    public static Vector add(final ReadableVector vector1, final ReadableVector vector2) {
        return new Vector(vector1.x() + vector2.x(), vector1.y() + vector2.y());
    }

    public static Vector subtract(final ReadableVector vector1, final ReadableVector vector2) {
        return add(vector1, new Vector(vector2).negate());
    }

    public static Vector pointToPointVector(final ReadableVector vector1, final ReadableVector vector2) {
        return subtract(vector2, vector1);
    }

    @Override
    public void draw(final Graphics2D graphics) {
        draw(graphics, Color.RED, Point.ZERO);
    }

    @Override
    public void draw(final Graphics2D graphics, final Color colour) {
        draw(graphics, colour, Point.ZERO);
    }

    @Override
    public void draw(final Graphics2D graphics, final Color colour, final ReadablePoint location) {
        final GraphicsConfig graphics_config = new GraphicsConfig(graphics);
        graphics.setColor(java.awt.Color.WHITE);
        graphics.setStroke(new BasicStroke(1));
        graphics.drawLine(location.getSnappedX(), location.getSnappedY(), location.getSnappedX() + snappedX(),
                location.getSnappedY() + snappedY());
        graphics.setStroke(new BasicStroke(2));
        graphics.drawRect(location.getSnappedX(), location.getSnappedY(), 1, 1);
        graphics.setColor(colour);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawRect(location.getSnappedX() + snappedX(), location.getSnappedY() + snappedY(), 1, 1);
        graphics_config.set(graphics);
    }

    public float[] toArray() {
        final float[] vector = { x, y };
        return vector;
    }
}
