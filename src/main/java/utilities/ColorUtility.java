package utilities;

public class ColorUtility {
    private static final String BACKGROUND_COLOR = "#3DDC97";
    private static final String DYNAMIC_COLOR = "#46237A";
    private static final String FOREGROUND_COLOR = "#FCFCFC";
    private static final String ERROR_COLOR = "#f9e109";


    private ColorUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static String getBackgroundColor() {

        return BACKGROUND_COLOR;
    }

    public static String getDynamicColor() {
        return DYNAMIC_COLOR;
    }

    public static String getForegroundColor() {
        return FOREGROUND_COLOR;
    }

    public static String getErrorColor() {

        return ERROR_COLOR;
    }

}
