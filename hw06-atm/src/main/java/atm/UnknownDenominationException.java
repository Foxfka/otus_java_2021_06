package atm;

public class UnknownDenominationException extends RuntimeException {
    public UnknownDenominationException(String message) {
        super(message);
    }
}
