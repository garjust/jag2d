package garjust.jag2d.geometry;

public interface Geometry<G> extends Drawable {

    public G rotate(final float theta);

    public G scale(final float scalar);

    public G translate(final float x, final float y);
}
