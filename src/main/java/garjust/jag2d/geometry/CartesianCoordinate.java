package garjust.jag2d.geometry;

import garjust.jag2d.util.FloatMath;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public class CartesianCoordinate {

    protected float x;
    protected float y;

    public int snappedX() {
        return Math.round(x);
    }

    public int snappedY() {
        return Math.round(y);
    }

    public void snap() {
        x = snappedX();
        y = snappedY();
    }

    public CartesianCoordinate returnSnapped() {
        return new CartesianCoordinate(snappedX(), snappedY());
    }

    public void rotate(final float theta) {
        final float temp_x = x;
        x = x * FloatMath.cos(theta) - y * FloatMath.sin(theta);
        y = temp_x * FloatMath.sin(theta) + y * FloatMath.cos(theta);
    }

    public void scale(final float scalar) {
        if (scalar < 0) {
            throw new RuntimeException("Cannot scale by a negative number");
        }
        x *= scalar;
        y *= scalar;
    }

    public void translate(final float x, final float y) {
        this.x += x;
        this.y += y;
    }

    public float[] toArray() {
        final float[] coordinate = { x, y };
        return coordinate;
    }

    public CartesianCoordinate copy() {
        return new CartesianCoordinate(x, y);
    }
}
