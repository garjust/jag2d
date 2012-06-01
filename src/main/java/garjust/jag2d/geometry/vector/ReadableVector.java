package garjust.jag2d.geometry.vector;

import garjust.jag2d.geometry.Copyable;
import garjust.jag2d.geometry.Drawable;

public interface ReadableVector extends Drawable, Copyable<Vector> {

    public float x();

    public float y();

    public int snappedX();

    public int snappedY();

    public float length();

    public float angle();
}
