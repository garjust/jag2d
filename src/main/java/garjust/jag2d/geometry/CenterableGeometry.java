package garjust.jag2d.geometry;

import garjust.jag2d.geometry.point.ReadablePoint;

public interface CenterableGeometry extends Geometry {

    public void rotate(final float theta, final ReadablePoint center);
}
