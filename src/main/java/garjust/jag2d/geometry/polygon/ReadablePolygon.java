package garjust.jag2d.geometry.polygon;

import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.PointList;

public interface ReadablePolygon {

    public PointList vertices();

    public Point centre();

    public Polygon hull();
}
