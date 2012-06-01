package garjust.jag2d.geometry;

import garjust.jag2d.util.GraphicsConfig;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public abstract class CartesianCoordinate<C extends CartesianCoordinate<?>> implements Drawable {

    protected float x;
    protected float y;

    public int snappedX() {
        return Math.round(x);
    }

    public int snappedY() {
        return Math.round(y);
    }

    @Override
    public void draw(final Graphics2D graphics) {
        this.draw(graphics, java.awt.Color.RED);
    }

    @Override
    public void draw(final Graphics2D graphics, final Color colour) {
        final GraphicsConfig graphics_config = new GraphicsConfig(graphics);
        graphics.setColor(colour);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawRect(snappedX(), snappedY(), 1, 1);
        graphics_config.set(graphics);
    }

    public float[] toArray() {
        final float[] point = { x, y };
        return point;
    }
}
