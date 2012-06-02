package garjust.jag2d.geometry;

import garjust.jag2d.core.Drawable;

public interface Geometry<G> extends Drawable {

    public G rotate(final float theta);

    public G scale(final float scalar);

    public G translate(final float x, final float y);
}
