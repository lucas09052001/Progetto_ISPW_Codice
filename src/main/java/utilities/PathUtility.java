package utilities;

public class PathUtility {

    private static final String PATH_TO_EMPTY_PROFILE_PICTURE = "resources/ImageRepository/NoProfilePicture.png";
    private static final String PATH_TO_EMPTY_IMAGE = "resources/ImageRepository/EmptyImage.png";
    private static final String PATH_TO_LOAN_REQUEST_JSON = "resources/Json/loanRequests.json";
    private static final String PATH_TO_LOAN_POST_JSON = "resources/Json/loanPost.json";
    private static final String PATH_TO_LOAN_EFFECTIVE_JSON = "resources/Json/loanEffective.json";
    private static final String PATH_TO_DISCOUNT_JSON = "resources/Json/discount.json";
    private static final String PATH_TO_USER_JSON = "resources/Json/users.json";
    private static final String PATH_TO_LOGO_IMAGE = "resources/ImageRepository/Logo.png";
    private static final String PATH_TO_ZERO_STAR = "resources/ImageRepository/NoRating.png";
    private static final String PATH_TO_ONE_STAR = "resources/ImageRepository/1star.png";
    private static final String PATH_TO_TWO_STAR = "resources/ImageRepository/2star.png";
    private static final String PATH_TO_THREE_STAR = "resources/ImageRepository/3star.png";




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

    public static String getPathToEmptyProfilePicture() {
        return PATH_TO_EMPTY_PROFILE_PICTURE;
    }
}
