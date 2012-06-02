package garjust.jag2d.geometry.vector;

import static garjust.jag2d.test.CustomAssert.assertThatFloat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import garjust.jag2d.util.FloatMath;

import org.junit.Before;
import org.junit.Test;

public class VectorTest {

    private double acceptableError;
    private Vector quadrant1;
    private Vector quadrant2;
    private Vector quadrant3;
    private Vector quadrant4;

    @Before
    public void setup() throws Exception {
        acceptableError = 0.0001;
        quadrant1 = new Vector(10, 4);
        quadrant2 = new Vector(-3, 5.2f);
        quadrant3 = new Vector(-6, -7);
        quadrant4 = new Vector(3.4f, -10.1f);
    }

    @Test
    public void shouldHaveCorrectConstants() throws Exception {
        assertThat(Vector.ZERO.length(), is(0f));
        assertThat(Vector.X_UNIT_VECTOR.length(), is(1f));
        assertThat(Vector.X_UNIT_VECTOR.angle(), is(0f));
        assertThat(Vector.Y_UNIT_VECTOR.length(), is(1f));
        assertThat(Vector.Y_UNIT_VECTOR.angle(), is(FloatMath.PI / 2));
        assertThatFloat(Vector.DIAGONAL_VECTOR.length(), is(closeTo(1, acceptableError)));
        assertThatFloat(Vector.DIAGONAL_VECTOR.angle(), is(closeTo(Math.PI / 4, acceptableError)));
    }

    @Test
    public void shouldHaveCorrectLength() throws Exception {
        assertThatFloat(quadrant1.length(), is(closeTo(10.7703, acceptableError)));
        assertThatFloat(quadrant2.length(), is(closeTo(6.00333, acceptableError)));
        assertThatFloat(quadrant3.length(), is(closeTo(9.21954, acceptableError)));
        assertThatFloat(quadrant4.length(), is(closeTo(10.6569, acceptableError)));
    }

    @Test
    public void shouldCalculateCorrectAngle() throws Exception {
        assertThatFloat(quadrant1.angle(), is(closeTo(0.38051, acceptableError)));
        assertThatFloat(quadrant2.angle(), is(closeTo(2.09407, acceptableError)));
        assertThatFloat(quadrant3.angle(), is(closeTo(-2.38414, acceptableError)));
        assertThatFloat(quadrant4.angle(), is(closeTo(-1.24607, acceptableError)));
    }

    @Test
    public void shouldChangeTheMagnitudeOfVectors() throws Exception {
        Vector expectedVector = new Vector(1, 1);
        assertThat(quadrant1.translate(1, 1), is(expectedVector));

        expectedVector = new Vector(1, 1);
        assertThat(quadrant2.translate(1, 1), is(expectedVector));

        expectedVector = new Vector(1, 1);
        assertThat(quadrant3.translate(1, 1), is(expectedVector));

        expectedVector = new Vector(1, 1);
        assertThat(quadrant4.translate(1, 1), is(expectedVector));
    }

    @Test
    public void testSnap() throws Exception {
        final ReadableVector expected = new Vector(4, 8);
        final ReadableVector result = new Vector(3.9f, 8.4f).snap();
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
    }

    @Test
    public void shouldNegateVectors() throws Exception {
        final ReadableVector expected = new Vector(-15, 3);
        final ReadableVector result = new Vector(15, -3).negate();
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
    }

    @Test
    public void testUnit() throws Exception {
        final ReadableVector expected = new Vector(1, 0);
        final ReadableVector result = new Vector(55, 0).unit();
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
    }

    @Test
    public void testNormal() throws Exception {
        final ReadableVector expected = new Vector(-3, 8);
        final ReadableVector result = new Vector(8, 3).normal();
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
    }

    @Test
    public void testAngle_Vector_Vector() throws Exception {
        final float result = Vector.angle(new Vector(8, 7), new Vector(4, 4));
        final float expected = 3.814074835f;
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testDot() throws Exception {
        final float result = Vector.dot(new Vector(8, 7), new Vector(4, 4));
        final float expected = 60;
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testAdd() throws Exception {
        final ReadableVector result = Vector.add(new Vector(5, 4), new Vector(3, 2));
        final ReadableVector expected = new Vector(8, 6);
        assertEquals(expected.x(), result.x(), 0.001);
        assertEquals(expected.y(), result.y(), 0.001);
        final ReadableVector result2 = Vector.add(new Vector(5, 4), new Vector(-3, 2));
        final ReadableVector expected2 = new Vector(2, 6);
        assertEquals(expected2.x(), result2.x(), 0.001);
        assertEquals(expected2.y(), result2.y(), 0.001);
    }

    @Test
    public void testSubtract() throws Exception {
        Vector vector = Vector.subtract(new Vector(5, 4), new Vector(3, 2));
        assertEquals(2, vector.x(), 0.001);
        assertEquals(2, vector.y(), 0.001);
        vector = Vector.subtract(new Vector(5, 4), new Vector(-3, 2));
        assertEquals(8, vector.x(), 0.001);
        assertEquals(2, vector.y(), 0.001);
    }

    @Test
    public void testPointToPointVector_Vector_Vector() throws Exception {
        Vector vector = Vector.pointToPointVector(new Vector(4, 5), new Vector(7, 2));
        assertEquals(3, vector.x(), 0.001);
        assertEquals(-3, vector.y(), 0.001);
    }

    @Test
    public void testToArray() throws Exception {
        final float[] array = new Vector(1, 2).toArray();
        assertEquals(1, array[0], 0.001);
        assertEquals(2, array[1], 0.001);
    }
}
