package garjust.jag2d.geometry.point;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.Copyable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

public class PointList extends ArrayList<Point> implements Drawable, Copyable<PointList> {

    private static final long serialVersionUID = 1L;

    public PointList() {
        super();
    }

    public PointList(final int size) {
        super(size);
    }

    public PointList(final Collection<? extends Point> collection) {
        super(collection.size());
        for (Point point : collection) {
            add(point.copy());
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (Drawable drawable : this) {
            drawable.draw(graphics);
        }
    }

    public void drawConnected(final Graphics2D graphics) {
        final int[][] vertices = new int[2][size()];
        for (int i = 0; i < size(); i++) {
            vertices[0][i] = get(i).snappedX();
            vertices[1][i] = get(i).snappedY();
        }
        graphics.drawPolygon(vertices[0], vertices[1], size());
    }

    @Override
    public PointList copy() {
        final PointList list = new PointList();
        for (final Point point : this) {
            list.add(point.copy());
        }
        return list;
    }
}