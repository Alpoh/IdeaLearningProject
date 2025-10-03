import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Minimal unit tests for Welcome without external dependencies.
 *
 * It uses reflection to call the private static findAverage method and
 * basic assertions that throw AssertionError on failure.
 *
 * You can run it with: java -cp out\\production\\IdeaLearningProject WelcomeTest
 */
public class WelcomeTest {

    public static void main(String[] args) throws Exception {
        int passed = 0;
        int failed = 0;
        for (Method m : WelcomeTest.class.getDeclaredMethods()) {
            if (m.getName().startsWith("test") && m.getParameterCount() == 0) {
                try {
                    m.setAccessible(true);
                    m.invoke(null);
                    System.out.println("[PASS] " + m.getName());
                    passed++;
                } catch (Throwable t) {
                    Throwable cause = t.getCause() != null ? t.getCause() : t;
                    System.out.println("[FAIL] " + m.getName() + ": " + cause);
                    failed++;
                }
            }
        }
        System.out.println("Tests run: " + (passed + failed) + ", Passed: " + passed + ", Failed: " + failed);
        if (failed > 0) {
            throw new AssertionError("Some tests failed");
        }
    }

    // ---------------------- Tests ----------------------

    private static void testAverageOfTypicalArray() throws Exception {
        double avg = invokeFindAverage(new int[]{5, 6, 7, 8});
        assertEquals(6.5, avg, 1e-9, "Average of {5,6,7,8}");
    }

    private static void testAverageEmptyArrayIsNaN() throws Exception {
        double avg = invokeFindAverage(new int[]{});
        assertTrue(Double.isNaN(avg), "Average of empty array should be NaN, got " + avg);
    }

    private static void testAverageSingleElement() throws Exception {
        double avg = invokeFindAverage(new int[]{42});
        assertEquals(42.0, avg, 1e-9, "Average of single element {42}");
    }

    private static void testAverageWithNegatives() throws Exception {
        double avg = invokeFindAverage(new int[]{-2, 2});
        assertEquals(0.0, avg, 1e-9, "Average of {-2,2}");
    }

    private static void testMainPrintsExpectedAverage() throws Exception {
        // Capture System.out to verify main output contains the expected average
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            Welcome.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        String output = baos.toString();
        String expectedPart = "AVERAGE of array " + Arrays.toString(new int[]{5,6,7,8}) + " is 6.5";
        assertTrue(output.contains(expectedPart), "Main output should contain: '" + expectedPart + "' but was: '" + output.trim() + "'");
    }

    // ------------------- Test Helpers ------------------

    private static double invokeFindAverage(int[] values) throws Exception {
        Method m = Welcome.class.getDeclaredMethod("findAverage", int[].class);
        m.setAccessible(true);
        Object result = m.invoke(null, (Object) values);
        return (double) result;
    }

    private static void assertEquals(double expected, double actual, double delta, String message) {
        if (!(Math.abs(expected - actual) <= delta)) {
            throw new AssertionError(message + ": expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
