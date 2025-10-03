import java.util.Arrays;
import java.util.logging.Logger;

class Welcome {
    private static final Logger LOG = Logger.getLogger(Welcome.class.getName());

    public static void main(String[] args) {
        int[] array = {5, 6, 7, 8};
        LOG.info("Starting average calculation for array: " + Arrays.toString(array));
        double avg = findAverage(array);
        LOG.info("Computed average: " + avg);
        System.out.println("AVERAGE of array " + Arrays.toString(array) + " is " + avg);
    }

    private static double findAverage(int[] values) {
        return Arrays.stream(values)
                .average()
                .orElse(Double.NaN);
    }
}
