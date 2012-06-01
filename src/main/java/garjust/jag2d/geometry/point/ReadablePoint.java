package garjust.jag2d.geometry.point;

import java.awt.Color;
import java.awt.Graphics2D;

public interface ReadablePoint {

    public float x();

    public float y();

    public int snappedX();

    public int snappedY();

    public void draw(final Graphics2D graphics);

    public void draw(final Graphics2D graphics, final Color colour);

    public Point copy();
}
