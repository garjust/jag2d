package garjust.jag2d.geometry.point;

import garjust.jag2d.geometry.CartesianCoordinate;
import garjust.jag2d.geometry.Drawable;
import garjust.jag2d.geometry.vector.Vector;

public class Point extends CartesianCoordinate<Point> implements Drawable, ReadablePoint, MoveablePoint {

    public static final ReadablePoint ZERO = new Point(0, 0);

    public Point(final float x, final float y) {
        super(x, y);
    }

    public Point snap() {
        return new Point(snappedX(), snappedY());
    }

    public static Vector pointToPointVector(final ReadablePoint point1, final ReadablePoint point2) {
        return new Vector(point2.x() - point1.x(), point2.y() - point1.y());
    }

    @Override
    public Point copy() {
        return new Point(x, y);
    }
}
