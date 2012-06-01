package garjust.jag2d.geometry.rectangle;

import java.awt.Graphics2D;

/**
 *
 * @author jagarbut
 */
public interface ReadableRectangle {

    public int x();

    public int y();

    public int w();

    public int h();

    public void draw(final Graphics2D graphics);

    @Override
    public String toString();
}
