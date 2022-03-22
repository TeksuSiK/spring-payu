package pl.teksusik.payu.exception;

public class InvalidJsonException extends RuntimeException {
    public InvalidJsonException(String message) {
        super(String.format("There was an error when processing request: %s", message));
    }
}
