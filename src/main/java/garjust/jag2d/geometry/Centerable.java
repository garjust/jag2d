package garjust.jag2d.geometry;

import garjust.jag2d.geometry.point.ReadablePoint;

public interface Centerable<G> extends Geometry<G> {

    public G rotate(final float theta, final ReadablePoint center);
}
