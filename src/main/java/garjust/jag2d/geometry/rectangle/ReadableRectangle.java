package garjust.jag2d.geometry.rectangle;

import garjust.jag2d.geometry.point.Point;

import java.awt.Graphics2D;

public interface ReadableRectangle {

    public Point position();

    public int w();

    public int h();

    public void draw(final Graphics2D graphics);
}
