package garjust.jag2d.geometry;

import garjust.jag2d.core.Drawable;

public interface Geometry extends Drawable {

    public void rotate(final float theta);

    public void scale(final float scalar);

    public void translate(final float x, final float y);
}
