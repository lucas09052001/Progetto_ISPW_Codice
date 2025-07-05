package utilities;

public class PathUtility {

    private static final String PATH_TO_EMPTY_IMAGE = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png";
    private static final String PATH_TO_LOAN_REQUEST_JSON = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanRequests.json";
    private static final String PATH_TO_LOAN_POST_JSON = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanPost.json";
    private static final String PATH_TO_LOAN_EFFECTIVE_JSON = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanEffective.json";
    private static final String PATH_TO_DISCOUNT_JSON = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/discount.json";
    private static final String PATH_TO_USER_JSON = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/users.json";
    private static final String PATH_TO_LOGO_IMAGE = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Logo.png";
    private static final String PATH_TO_ZERO_STAR = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/NoRating.png";
    private static final String PATH_TO_ONE_STAR = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/1star.png";
    private static final String PATH_TO_TWO_STAR = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/2star.png";
    private static final String PATH_TO_THREE_STAR = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/3star.png";

    private PathUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static String getPathToEmptyImage() {
        return PATH_TO_EMPTY_IMAGE;
    }

    public static String getPathToLoanRequestJson() {
        return PATH_TO_LOAN_REQUEST_JSON;
    }

    public static String getPathToLoanPostJson() {
        return PATH_TO_LOAN_POST_JSON;
    }

    public static String getPathToLoanEffectiveJson() {
        return PATH_TO_LOAN_EFFECTIVE_JSON;
    }

    public static String getPathToDiscountJson() {
        return PATH_TO_DISCOUNT_JSON;
    }

    public static String getPathToUserJson() {
        return PATH_TO_USER_JSON;
    }

    public static String getPathToLogoImage() {
        return PATH_TO_LOGO_IMAGE;
    }

    public static String getPathToZeroStar() {
        return PATH_TO_ZERO_STAR;
    }

    public static String getPathToOneStar() {
        return PATH_TO_ONE_STAR;
    }

    public static String getPathToTwoStar() {
        return PATH_TO_TWO_STAR;
    }

    public static String getPathToThreeStar() {
        return PATH_TO_THREE_STAR;
    }
}
