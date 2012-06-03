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
    public void draw(Graphics2D graphics) {
        for (Drawable drawable : this) {
            drawable.draw(graphics);
        }
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