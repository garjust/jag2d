package garjust.jag2d.geometry.polygon;

import garjust.jag2d.geometry.Geometry;
import garjust.jag2d.geometry.point.ReadablePoint;

public interface MoveablePolygon extends Geometry<Polygon> {

    public Polygon rotate(final float theta, final ReadablePoint center);
}
