package garjust.jag2d.geometry.vector;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.Copyable;

public interface ReadableVector extends Drawable, Copyable<Vector> {

    public float x();

    public float y();

    public int snappedX();

    public int snappedY();

    public float length();

    public float angle();

    public Vector returnSnapped();

    public Vector returnNegated();

    public Vector returnUnit();

    public Vector returnNormal();

    public float angle(ReadableVector otherVector);

    public float dot(final ReadableVector otherVector);

    public Vector add(final ReadableVector otherVector);

    public Vector subtract(final ReadableVector otherVector);

    public Vector pointToPointVector(final ReadableVector other);
}
