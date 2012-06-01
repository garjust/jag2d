package garjust.jag2d.geometry;

public interface Geometry {

    public <G> G rotate(final float theta);

    public <G> G scale(final float scalar);

    public <G> G translate(final float x, final float y);
}
