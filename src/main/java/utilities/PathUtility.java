package utilities;

public class PathUtility {
    private static final String pathToEmptyImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png";
    private static final String pathToLoanRequestJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanRequests.json";
    private static final String pathToLoanPostJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanPost.json";
    private static final String pathToLoanEffectiveJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanEffective.json";
    private static final String pathToDiscountJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/discount.json";
    private static final String pathToUserJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/users.json";
    private static final String pathToLogoImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Logo.png";
    private static final String pathToZeroStar = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/NoRating.png";
    private static final String pathToOneStar = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/1star.png";
    private static final String pathToTwoStar = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/2star.png";
    private static final String pathToThreeStar = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/3star.png";


    private PathUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static String getPathToEmptyImage(){
        return pathToEmptyImage;
    }

    public static String getPathToLoanRequestJson() {
        return pathToLoanRequestJson;
    }

    public static String getPathToLoanPostJson() {
        return pathToLoanPostJson;
    }

    public static String getPathToLoanEffectiveJson() {
        return pathToLoanEffectiveJson;
    }

    public static String getPathToDiscountJson() {
        return pathToDiscountJson;
    }

    public static String getPathToUserJson() {
        return pathToUserJson;
    }

    public static String getPathToLogoImage() {
        return pathToLogoImage;
    }

    public static String getPathToZeroStar() {
        return pathToZeroStar;
    }

    public static String getPathToOneStar() {
        return pathToOneStar;
    }

    public static String getPathToTwoStar() {
        return pathToTwoStar;
    }

    public static String getPathToThreeStar() {
        return pathToThreeStar;
    }
}

