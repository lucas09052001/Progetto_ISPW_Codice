package boundery;

public class Color_Repository {
    private static final String BACKGROUND_COLOR = "#3DDC97";
    private static final String ERROR_COLOR = "#ED4E50";

    private Color_Repository() {
        throw new IllegalStateException("Utility class");
    }

    public static String getBackground_color() {
        return BACKGROUND_COLOR;
    }

    public static String getError_color() {
        return ERROR_COLOR;
    }

}
