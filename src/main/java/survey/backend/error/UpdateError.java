package survey.backend.error;

public class UpdateError extends RuntimeException {

    public UpdateError(String message) {
        super(message);
    }

    public static UpdateError updateError(String itemType) {
        return new UpdateError("The " + itemType + " could not be updated");
    }
}