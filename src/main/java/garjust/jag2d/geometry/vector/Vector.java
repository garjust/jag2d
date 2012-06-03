package garjust.jag2d.geometry.vector;

import garjust.jag2d.geometry.CartesianCoordinate;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.ReadablePoint;
import garjust.jag2d.util.FloatMath;

import java.awt.Graphics2D;

/**
 * Mutable vector class
 * 
 * Interfaces exist to limit the mutability of this vector class, and allow for
 * Immutable implementations if desired
 * 
 * Geometric transformations affect the current object while other methods
 * return new vectors
 */
public class Vector extends CartesianCoordinate implements ReadableVector, MoveableVector {

    public static final ReadableVector ZERO = new Vector(0, 0);
    public static final ReadableVector X_UNIT_VECTOR = new Vector(1, 0);
    public static final ReadableVector Y_UNIT_VECTOR = new Vector(0, 1);
    public static final ReadableVector DIAGONAL_VECTOR = new Vector(1, 1).returnUnit();

    public Vector(final float x, final float y) {
        super(x, y);
    }

    @Override
    public float length() {
        return FloatMath.sqrt(x * x + y * y);
    }

    @Override
    public float angle() {
        return x == 0 && y == 0 ? 0 : FloatMath.acos(x / length());
    }

    public Vector length(final float new_length) {
        final ReadableVector unit = returnUnit();
        return new Vector(unit.x() * new_length, unit.y() * new_length);
    }

    @Override
    public Vector returnSnapped() {
        return new Vector(snappedX(), snappedY());
    }

    public Vector negate() {
        x = -x;
        y = -y;
        return this;
    }

    @Override
    public Vector returnNegated() {
        return new Vector(-x, -y);
    }

    public Vector unit() {
        final float length = length();
        x /= length;
        y /= length;
        return this;
    }

    @Override
    public Vector returnUnit() {
        final float length = length();
        return new Vector(x / length, y / length);
    }

    public Vector normal() {
        final float hold = x;
        x = -y;
        y = hold;
        return this;
    }

    @Override
    public Vector returnNormal() {
        return new Vector(-y, x);
    }

    @Override
    public float angle(ReadableVector otherVector) {
        return FloatMath.acos(dot(otherVector) / (length() * otherVector.length())) * 180 / FloatMath.PI;
    }

    @Override
    public float dot(final ReadableVector otherVector) {
        return x * otherVector.x() + y * otherVector.y();
    }

    @Override
    public Vector add(final ReadableVector otherVector) {
        return new Vector(x + otherVector.x(), y + otherVector.y());
    }

    @Override
    public Vector subtract(final ReadableVector otherVector) {
        return add(otherVector.copy().returnNegated());
    }

    @Override
    public Vector pointToPointVector(final ReadableVector otherVector) {
        return otherVector.subtract(this);
    }

    @Override
    public void draw(final Graphics2D graphics) {
        draw(graphics, Point.ZERO);
    }

    public void draw(final Graphics2D graphics, final ReadablePoint location) {
        graphics.drawLine(location.snappedX(), location.snappedY(), location.snappedX() + snappedX(), location.snappedY() + snappedY());
        graphics.fillRect(location.snappedX() + snappedX(), location.snappedY() + snappedY(), 2, 2);
    }

    @Override
    public Vector copy() {
        return new Vector(x, y);
    }
}
