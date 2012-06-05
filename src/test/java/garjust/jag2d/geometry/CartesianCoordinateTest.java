package garjust.jag2d.geometry;

import static garjust.jag2d.test.CustomAssert.assertThatFloat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import garjust.jag2d.util.FloatMath;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {

    private double acceptableError;
    private CartesianCoordinate quadrant1;
    private CartesianCoordinate quadrant2;
    private CartesianCoordinate quadrant3;
    private CartesianCoordinate quadrant4;

    @Before
    public void setup() throws Exception {
        acceptableError = 0.0001;
        quadrant1 = new CartesianCoordinate(10, 4);
        quadrant2 = new CartesianCoordinate(-3, 5.2f);
        quadrant3 = new CartesianCoordinate(-6, -7);
        quadrant4 = new CartesianCoordinate(3.4f, -10.1f);
    }

    @Test
    public void shouldGiveSnappedValues() throws Exception {
        final int expectedX = 15;
        final int expectedY = 31;
        final CartesianCoordinate testable = new CartesianCoordinate(15.3f, 30.5f);

        assertThat(testable.snappedX(), is(expectedX));
        assertThat(testable.snappedY(), is(expectedY));
    }

    @Test
    public void shouldGiveSnappedCoordinate() throws Exception {
        CartesianCoordinate expectedCoordinate = new CartesianCoordinate(15, 31);
        CartesianCoordinate coordinate = new CartesianCoordinate(15.3f, 30.5f);

        assertThat(coordinate.returnSnapped(), is(expectedCoordinate));
    }

    @Test
    public void shouldSnapCoordinates() throws Exception {
        CartesianCoordinate expectedCoordinates = new CartesianCoordinate(3, -10);

        quadrant4.snap();

        assertThat(quadrant4, is(expectedCoordinates));
    }

    @Test
    public void shouldRotateCoordinates() throws Exception {
        quadrant1.rotate(FloatMath.PI);
        assertThatFloat(quadrant1.x(), is(closeTo(-10, acceptableError)));
        assertThatFloat(quadrant1.y(), is(closeTo(-4, acceptableError)));

        quadrant2.rotate(FloatMath.PI / 4);
        assertThatFloat(quadrant2.x(), is(closeTo(-5.79828, acceptableError)));
        assertThatFloat(quadrant2.y(), is(closeTo(1.55563, acceptableError)));

        quadrant3.rotate(-1 * FloatMath.PI / 2);
        assertThatFloat(quadrant3.x(), is(closeTo(-7, acceptableError)));
        assertThatFloat(quadrant3.y(), is(closeTo(6, acceptableError)));

        quadrant4.rotate(FloatMath.PI / 43);
        assertThatFloat(quadrant4.x(), is(closeTo(4.12818, acceptableError)));
        assertThatFloat(quadrant4.y(), is(closeTo(-9.82487, acceptableError)));
    }

    @Test
    public void shouldScaleCoordinates() throws Exception {
        quadrant1.scale(2);
        assertThat(quadrant1.x(), is(20f));
        assertThat(quadrant1.y(), is(8f));

        quadrant2.scale(1 / 2.7f);
        assertThatFloat(quadrant2.x(), is(closeTo(-1.11111, acceptableError)));
        assertThatFloat(quadrant2.y(), is(closeTo(1.92593, acceptableError)));

        quadrant3.scale(3.4f);
        assertThatFloat(quadrant3.x(), is(closeTo(-20.4f, acceptableError)));
        assertThatFloat(quadrant3.y(), is(closeTo(-23.8f, acceptableError)));

        quadrant4.scale(1);
        assertThat(quadrant4.x(), is(3.4f));
        assertThat(quadrant4.y(), is(-10.1f));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowUncheckedExceptionWhenScaleWithNegativeScalar() throws Exception {
        quadrant1.scale(-1);
    }

    @Test
    public void shouldTranslateCoordinates() throws Exception {
        quadrant1.translate(0, 0);
        assertThat(quadrant1.x(), is(10f));
        assertThat(quadrant1.y(), is(4f));

        quadrant2.translate(1.1f, -1.1f);
        assertThatFloat(quadrant2.x(), is(closeTo(-1.9f, acceptableError)));
        assertThatFloat(quadrant2.y(), is(closeTo(4.1f, acceptableError)));

        quadrant3.translate(20, 5);
        assertThatFloat(quadrant3.x(), is(closeTo(14, acceptableError)));
        assertThatFloat(quadrant3.y(), is(closeTo(-2, acceptableError)));

        quadrant4.translate(-1, -1);
        assertThatFloat(quadrant4.x(), is(closeTo(2.4f, acceptableError)));
        assertThatFloat(quadrant4.y(), is(closeTo(-11.1f, acceptableError)));
    }

    @Test
    public void shouldTranslatesToArray() throws Exception {
        float[] array = quadrant2.toArray();

        assertThat(array[0], is(quadrant2.x()));
        assertThat(array[1], is(quadrant2.y()));
    }

    @Test
    public void shouldMakeDeepCopy() throws Exception {
        CartesianCoordinate copyable = quadrant3.copy();

        assertThat(copyable, is(quadrant3));
        assertTrue(copyable != quadrant3);

        copyable.x(5433);
        copyable.y(5434);

        assertThat(copyable, is(not(quadrant3)));
    }
}
