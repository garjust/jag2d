package garjust.jag2d.geometry.vector;

public interface CopyableVector extends ReadableVector {

    public Vector length(final float new_length);

    public Vector snap();

    public Vector negate();

    public Vector unit();

    public Vector normal();
}