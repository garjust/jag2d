package garjust.jag2d.geometry.vector;

import garjust.jag2d.geometry.point.ReadablePoint;

import java.awt.Color;
import java.awt.Graphics2D;

public interface ReadableVector {

    public float x();

    public float y();

    public int snappedX();

    public int snappedY();

    public float length();

    public float angle();

    public void draw(final Graphics2D graphics);

    public void draw(final Graphics2D graphics, final Color colour);

    public void draw(final Graphics2D graphics, final Color colour, final ReadablePoint position);
}
