package garjust.jag2d.geometry.rectangle;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.Copyable;
import garjust.jag2d.geometry.point.Point;

public interface ReadableRectangle extends Drawable, Copyable<Rectangle> {

    public Point origin();

    public int w();

    public int h();
}
