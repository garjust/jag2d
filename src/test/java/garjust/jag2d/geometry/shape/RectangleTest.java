package garjust.jag2d.geometry.shape;

import static org.junit.Assert.assertEquals;
import garjust.jag2d.geometry.point.Point;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {

    private Rectangle rectangleOne;
    private Rectangle rectangleTwo;

    @Before
    public void setup() throws Exception {
        rectangleOne = new Rectangle(new Point(0, 10), new Point(10, 0));
        rectangleTwo = new Rectangle(new Point(5, 5), new Point(9, -3));
    }

    @Test
    public void testScale() {
        final Rectangle expected = new Rectangle(0, 10, 40, 40);
        final Rectangle result = new Rectangle(0, 10, 4, 4).scale(10);
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
        assertEquals(expected.w(), result.w(), 0.001);
        assertEquals(expected.h(), result.h(), 0.001);
    }

    @Test
    public void testTranslate() {
        final Rectangle expected = new Rectangle(3, 11, 20, 20);
        final Rectangle result = new Rectangle(0, 5, 20, 20).translate(3, 6);
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
        assertEquals(expected.w(), result.w(), 0.001);
        assertEquals(expected.h(), result.h(), 0.001);
    }
}
