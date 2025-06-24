package boundery;

public class ColorRepository {
    private static final String BACKGROUND_COLOR = "#3DDC97";
    private static final String ERROR_COLOR = "#ED4E50";

    private ColorRepository() {
        throw new IllegalStateException("Utility class");
    }

    public static String getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public static String getErrorColor() {
        return ERROR_COLOR;
    }

}
