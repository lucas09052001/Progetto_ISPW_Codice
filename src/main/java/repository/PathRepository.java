package repository;

public class PathRepository {
    private static final String pathToEmptyImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png";
    private static final String pathToLoanRequestJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanRequests.json";
    private static final String pathToLoanPostJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanPost.json";
    private static final String pathToLoanEffectiveJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanEffective.json";

    private PathRepository() {
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
}
