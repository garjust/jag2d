package garjust.jag2d.geometry.shape;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.point.PointList;

import org.junit.Before;
import org.junit.Test;

public class PolygonTest {

    private Polygon polygonOne;
    private Polygon polygonTwo;
    private PointList polygonOneVertices;
    private PointList polygonTwoVertices;

    @Before
    public void setup() throws Exception {
        polygonOneVertices = new PointList();
        polygonOneVertices.add(new Point(0, 0));
        polygonOneVertices.add(new Point(10, 0));
        polygonOneVertices.add(new Point(10, 10));
        polygonOneVertices.add(new Point(0, 10));
        polygonOne = new Polygon(polygonOneVertices);

        polygonTwoVertices = new PointList();
        polygonTwoVertices.add(new Point(3, 5));
        polygonTwoVertices.add(new Point(7, 4));
        polygonTwoVertices.add(new Point(-3, -3));
        polygonTwo = new Polygon(polygonTwoVertices);
    }

    @Test
    public void shouldMakeDeepCopy() throws Exception {
        Polygon copyable = polygonOne.copy();

        assertThat(copyable, is(polygonOne));
        assertTrue(copyable != polygonOne);

        polygonOneVertices.clear();
        // polygonOneVertices.add(new Point(123123, 123123));

        assertThat(copyable, is(not(polygonOne)));
    }

    @Test
    public void testScale() {
        final Polygon result = new Polygon(polygonTwoVertices);
        result.scale(2);
        final PointList expected = new PointList(3);
        expected.add(new Point(3.666666667f, 8));
        expected.add(new Point(11.6666667f, 6));
        expected.add(new Point(-8.333333333f, -8));
        assert (result.vertices().get(0).equals(expected.get(0)));
        assert (result.vertices().get(1).equals(expected.get(1)));
        assert (result.vertices().get(2).equals(expected.get(2)));
    }

    @Test
    public void testTranslate() {
        final Polygon result = new Polygon(polygonTwoVertices);
        result.translate(3, 6);
        final PointList expected = new PointList(3);
        expected.add(new Point(6, 11));
        expected.add(new Point(10, 10));
        expected.add(new Point(0, 3));
        assert (result.vertices().get(0).equals(expected.get(0)));
        assert (result.vertices().get(1).equals(expected.get(1)));
        assert (result.vertices().get(2).equals(expected.get(2)));
    }
}
