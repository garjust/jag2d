package garjust.jag2d.collision;

import garjust.jag2d.geometry.point.Point;

import java.awt.Color;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public final class BoundingBox implements BoundingGeometry {

    private final Point ul;
    private final Point lr;

    public BoundingBox(final Point ul, final Point lr) {
        this.ul = ul;
        this.lr = lr;
    }

    public Point ul() {
        return ul.copy();
    }

    public Point lr() {
        return lr.copy();
    }

    public void draw(final java.awt.Graphics2D graphics) {
        final Color color_save = graphics.getColor();
        graphics.setColor(Color.GREEN);
        graphics.drawRect((int) ul.x(), (int) ul.y(), (int) (lr.x() - ul.x()), (int) (lr.y() - ul.y()));
        graphics.setColor(color_save);
    }

    public static boolean intersection(final BoundingBox box1, final BoundingBox box2) {
        if (box1.ul.x() > box2.lr.x() || box2.ul.x() > box1.lr.x() || box1.ul.y() > box2.lr.y() || box2.ul.y() > box1.lr.y()) {
            return false;
        }
        return true;
    }
}
