package survey.backend.error;

public class NoDataFoundError extends RuntimeException {

    public NoDataFoundError(String message) {
        super(message);
    }

    public static NoDataFoundError withId(String itemType, int id) {
        return new NoDataFoundError(itemType + " not found");
    }

    public static NoDataFoundError noResults(String itemType, String message) {
        return new NoDataFoundError(itemType + " return 0 results with " + message);
    }
}