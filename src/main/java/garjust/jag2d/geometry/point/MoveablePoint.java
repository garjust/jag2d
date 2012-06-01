package garjust.jag2d.geometry.point;


/**
 *
 * @author jagarbut
 */
public interface MoveablePoint {

    public Point rotate(final float theta);

    public Point rotate(final float theta, final ReadablePoint center);

    public Point scale(final float scalar);

    public Point translate(final float x, final float y);
}
