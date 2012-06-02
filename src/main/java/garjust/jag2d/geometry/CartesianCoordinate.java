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

    public CartesianCoordinate snap() {
        return new CartesianCoordinate(snappedX(), snappedY());
    }

    public CartesianCoordinate rotate(final float theta) {
        final float temp_x = x;
        x = x * FloatMath.cos(theta) - y * FloatMath.sin(theta);
        y = temp_x * FloatMath.sin(theta) + y * FloatMath.cos(theta);
        return this;
    }

    public CartesianCoordinate scale(final float scalar) {
        if (scalar < 0) {
            throw new RuntimeException("Cannot scale by a negative number");
        }
        x *= scalar;
        y *= scalar;
        return this;
    }

    public CartesianCoordinate translate(final float x, final float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public float[] toArray() {
        final float[] coordinate = { x, y };
        return coordinate;
    }

    public CartesianCoordinate copy() {
        return new CartesianCoordinate(x, y);
    }
}
