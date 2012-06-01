package garjust.jag2d.geometry.vector;

import garjust.jag2d.geometry.CartesianCoordinate;
import garjust.jag2d.util.FloatMath;
import lombok.experimental.Accessors;

/**
 * Mutable vector class
 * 
 * Interfaces exist to limit the mutability of this vector class, and allow for
 * Immutable implementations if desired
 * 
 * Geometric transformations affect the current object while other methods
 * return new vectors
 */
@Accessors(fluent = true)
public class Vector extends CartesianCoordinate<Vector> implements ReadableVector, MoveableVector, CopyableVector {

    public static final ReadableVector ZERO = new Vector(0, 0);
    public static final ReadableVector X_UNIT_VECTOR = new Vector(1, 0);
    public static final ReadableVector Y_UNIT_VECTOR = new Vector(0, 1);
    public static final ReadableVector DIAGONAL_VECTOR = new Vector(1, 1).unit();

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

    @Override
    public Vector length(final float new_length) {
        final ReadableVector unit = unit();
        return new Vector(unit.x() * new_length, unit.y() * new_length);
    }

    @Override
    public Vector snap() {
        return new Vector(Math.round(x), Math.round(y));
    }

    @Override
    public Vector negate() {
        return new Vector(-x, -y);
    }

    @Override
    public Vector unit() {
        final float magnitude = length();
        return new Vector(x / magnitude, y / magnitude);
    }

    @Override
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
        return add(vector1, vector2.copy().negate());
    }

    public static Vector pointToPointVector(final ReadableVector vector1, final ReadableVector vector2) {
        return subtract(vector2, vector1);
    }

    @Override
    public Vector copy() {
        return new Vector(x, y);
    }
}
