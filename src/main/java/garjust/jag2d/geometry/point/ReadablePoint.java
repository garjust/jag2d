package garjust.jag2d.geometry.point;

import garjust.jag2d.geometry.Drawable;

public interface ReadablePoint extends Drawable {

    public float x();

    public float y();

    public int snappedX();

    public int snappedY();

    public Point copy();
}
