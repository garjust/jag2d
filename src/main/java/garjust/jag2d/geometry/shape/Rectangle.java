package garjust.jag2d.geometry.shape;

import garjust.jag2d.collision.BoundingBox;
import garjust.jag2d.collision.Collidable;
import garjust.jag2d.geometry.CenterableGeometry;
import garjust.jag2d.geometry.Copyable;
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
public final class Rectangle implements Collidable, CenterableGeometry, Copyable<Rectangle> {

    public static final Rectangle square = new Rectangle(new Point(0, 1), new Point(1, 0));
    private final Point ul;
    private final Point lr;

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
        ul.scale(scalar);
        lr.scale(scalar);
    }

    @Override
    public void translate(final float x, final float y) {
        ul.translate(x, y);
        lr.translate(x, y);
    }

    @Override
    public BoundingBox bound() {
        return new BoundingBox(ul, lr);
    }

    @Override
    public void draw(final java.awt.Graphics2D graphics) {
        graphics.drawRect(ul.snappedX(), ul.snappedY(), ul.snappedX() - lr.snappedX(), ul.snappedY() - lr.snappedY());
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(ul.copy(), lr.copy());
    }
}
