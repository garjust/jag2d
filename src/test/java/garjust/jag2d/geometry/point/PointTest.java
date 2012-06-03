package garjust.jag2d.geometry.point;

import static org.junit.Assert.assertEquals;
import garjust.jag2d.geometry.vector.Vector;
import garjust.jag2d.util.FloatMath;

import org.junit.Before;
import org.junit.Test;

public class PointTest {

    @Before
    public void setup() throws Exception {}

    @Test
    public void testRotate_point() {
        final ReadablePoint expected = new Point(0, 5);
        final ReadablePoint result = new Point(5, 0).rotate(FloatMath.PI / 2, Point.ZERO);
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
    }

    @Test
    public void testSnap() {
        final ReadablePoint expected = new Point(4, 8);
        final ReadablePoint result = new Point(3.9f, 8.4f).returnSnapped();
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
    }

    @Test
    public void testPointToPointVector_Point_Point() {
        Vector vector = Point.pointToPointVector(new Point(4, 5), new Point(7, 2));
        assertEquals(3, vector.x(), 0.001);
        assertEquals(-3, vector.y(), 0.001);
    }
}
