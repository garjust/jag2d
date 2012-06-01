package garjust.jag2d.geometry.circle;

import garjust.jag2d.geometry.point.Point;

public interface ReadableCircle {

    public Point center();

    public float radius();

    public int snappedRadius();
}
