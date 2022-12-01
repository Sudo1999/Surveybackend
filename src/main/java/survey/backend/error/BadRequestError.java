package survey.backend.error;

public class BadRequestError extends RuntimeException {

    public BadRequestError(String message) {
        super(message);
    }

    public static BadRequestError withNoArg(String message) {
        return new BadRequestError(message);
    }
}