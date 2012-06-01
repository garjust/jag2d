package garjust.jag2d.geometry.point;

import garjust.jag2d.geometry.Geometry;

public interface MoveablePoint extends Geometry<Point> {

    public Point rotate(final float theta, final ReadablePoint center);
}
