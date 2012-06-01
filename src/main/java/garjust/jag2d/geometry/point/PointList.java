package garjust.jag2d.geometry.point;

import java.util.ArrayList;

public final class PointList extends ArrayList<Point> {

    private static final long serialVersionUID = 1L;

    public PointList() {
        super();
    }

    public PointList(final int size) {
        super(size);
    }

    public PointList(final PointList points) {
        super(points.size());
        for (ReadablePoint point : points) {
            add(new Point(point));
        }
    }

    public int[][] getCoordinateMatrix() {
        final int[][] matrix = new int[2][size()];
        int i = 0;
        for (Point point : this) {
            matrix[0][i] = point.snappedX();
            matrix[1][i] = point.snappedY();
            i++;
        }
        return matrix;
    }
}