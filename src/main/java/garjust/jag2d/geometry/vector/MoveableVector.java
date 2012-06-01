package garjust.jag2d.geometry.vector;

import garjust.jag2d.geometry.Geometry;

public interface MoveableVector extends Geometry {

    @Override
    public Vector rotate(final float theta);

    @Override
    public Vector scale(final float scalar);

    @Override
    public Vector translate(final float x, final float y);
}
