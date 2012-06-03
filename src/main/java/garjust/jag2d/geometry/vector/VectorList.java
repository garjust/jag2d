package garjust.jag2d.geometry.vector;

import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.Copyable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

public class VectorList extends ArrayList<Vector> implements Drawable, Copyable<VectorList> {

    private static final long serialVersionUID = 1L;

    public VectorList() {
        super();
    }

    public VectorList(int size) {
        super(size);
    }

    public VectorList(final Collection<? extends Vector> collection) {
        super(collection.size());
        for (Vector vector : collection) {
            add(vector.copy());
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (Drawable drawable : this) {
            drawable.draw(graphics);
        }
    }

    @Override
    public VectorList copy() {
        final VectorList list = new VectorList();
        for (final Vector vector : this) {
            list.add(vector.copy());
        }
        return list;
    }
}
