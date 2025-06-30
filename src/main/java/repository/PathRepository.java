package repository;

public class PathRepository {
    private static final String pathToEmptyImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/EmptyImage.png";
    private static final String pathToLoanRequestJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/LoanRequests.json";

    private PathRepository() {
        throw new IllegalStateException("Utility class");
    }

    public static String getPathToEmptyImage(){
        return pathToEmptyImage;
    }

    public static String getPathToLoanRequestJson() {
        return pathToLoanRequestJson;
    }
}
