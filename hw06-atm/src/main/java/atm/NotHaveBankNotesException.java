package atm;

public class NotHaveBankNotesException extends RuntimeException {
    public NotHaveBankNotesException(String message) {
        super(message);
    }
}
