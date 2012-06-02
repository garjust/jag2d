package garjust.jag2d.test;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;

public class CustomAssert {

    public static void assertThatFloat(float expected, Matcher<Double> matcher) {
        assertThat((double) expected, matcher);
    }
}
