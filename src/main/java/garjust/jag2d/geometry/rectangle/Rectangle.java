package garjust.jag2d.geometry.rectangle;

import garjust.jag2d.collision.BoundingBox;
import garjust.jag2d.collision.Collidable;
import garjust.jag2d.core.Drawable;
import garjust.jag2d.geometry.point.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
@Log4j
public final class Rectangle implements Collidable, Drawable, ReadableRectangle, MoveableRectangle {

    public static final Rectangle square = new Rectangle(new Point(1, 1), 1, 1);
    private final Point position;
    private int w;
    private int h;

    @Override
    public Rectangle rotate(final float theta) {
        log.error("Cannot rotate an axis aligned rectangle");
        return this;
    }

    @Override
    public Rectangle scale(final float scalar) {
        this.h *= scalar;
        this.w *= scalar;
        return this;
    }

    @Override
    public Rectangle translate(final float x, final float y) {
        position.translate(x, y);
        return this;
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(this);
    }

    @Override
    public void draw(final java.awt.Graphics2D graphics) {
        graphics.drawRect(position.snappedX(), position.snappedY(), w, h);
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(position.copy(), w, h);
    }
}
