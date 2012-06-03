package garjust.jag2d.geometry.circle;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.Copyable;
import garjust.jag2d.geometry.point.Point;

public interface ReadableCircle extends Drawable, Copyable<Circle> {

    public Point center();

    public float radius();

    public int snappedRadius();
}
