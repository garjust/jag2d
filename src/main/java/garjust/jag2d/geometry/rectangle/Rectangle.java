package garjust.jag2d.geometry.rectangle;

import garjust.jag2d.collision.BoundingBox;
import garjust.jag2d.collision.Collidable;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.ReadablePoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
@Log4j
public final class Rectangle implements Collidable, ReadableRectangle, MoveableRectangle {

    public static final Rectangle square = new Rectangle(Point.ZERO.copy(), 1, 1);
    private final Point origin;
    private int w;
    private int h;

    @Override
    public void rotate(final float theta) {
        log.error("Cannot rotate an axis aligned rectangle");
    }

    @Override
    public void rotate(final float theta, final ReadablePoint center) {
        log.error("Cannot rotate an axis aligned rectangle");
    }

    @Override
    public void scale(final float scalar) {
        this.h *= scalar;
        this.w *= scalar;
    }

    @Override
    public void translate(final float x, final float y) {
        origin.translate(x, y);
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(this);
    }

    @Override
    public void draw(final java.awt.Graphics2D graphics) {
        graphics.drawRect(origin.snappedX(), origin.snappedY(), w, h);
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(origin.copy(), w, h);
    }
}
