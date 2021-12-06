package atm;

public class BankNoteImpl implements BankNote {
    private final int denomination;

    public BankNoteImpl(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public int getDenomination() {
        return this.denomination;
    }
}
