package garjust.jag2d.geometry.vector;

import garjust.jag2d.core.Drawable;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class VectorList extends ArrayList<Vector> implements Drawable {

    private static final long serialVersionUID = 1L;

    public VectorList() {
        super();
    }

    public VectorList(int size) {
        super(size);
    }

    public VectorList(VectorList vectors) {
        super(vectors.size());
        for (Vector vector : vectors) {
            add(vector);
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (Drawable drawable : this) {
            drawable.draw(graphics);
        }
    }
}
