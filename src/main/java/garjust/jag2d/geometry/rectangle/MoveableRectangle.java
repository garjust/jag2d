package garjust.jag2d.geometry.rectangle;

import garjust.jag2d.geometry.point.ReadablePoint;

/**
 *
 * @author jagarbut
 */
public interface MoveableRectangle {

    public Rectangle rotate(final float theta);

    public Rectangle rotate(final float theta, final ReadablePoint center);

    public Rectangle scale(final float scalar);

    public Rectangle translate(final float x, final float y);
}
