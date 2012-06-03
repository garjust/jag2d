package garjust.jag2d.geometry.point;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.Copyable;

public interface ReadablePoint extends Drawable, Copyable<Point> {

    public float x();

    public float y();

    public int snappedX();

    public int snappedY();
}
