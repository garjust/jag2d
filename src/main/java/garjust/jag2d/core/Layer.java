package garjust.jag2d.core;

import garjust.jag2d.core.util.EntityList;

public class Layer extends EntityList {

    private static final long serialVersionUID = 1L;

    public Layer() {
        super();
    }

    public Layer(final int size) {
        super(size);
    }

    public void draw(java.awt.Graphics2D graphics) {
        for (Entity entity : this) {
            entity.draw(graphics);
        }
    }

    public void update(final float delta) {
        for (Entity entity : this) {
            entity.update(delta);
        }
    }
}
