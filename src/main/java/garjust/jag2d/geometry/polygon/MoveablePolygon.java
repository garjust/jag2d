package garjust.jag2d.geometry.polygon;

import garjust.jag2d.geometry.point.ReadablePoint;

/**
 *
 * @author jagarbut
 */
public interface MoveablePolygon {

    public Polygon rotate(final float theta);

    public Polygon rotate(final float theta, final ReadablePoint center);

    public Polygon scale(final float scalar);

    public Polygon translate(final float x, final float y);
}
