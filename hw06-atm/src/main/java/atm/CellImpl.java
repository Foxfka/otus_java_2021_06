package atm;

import java.util.List;

public class CellImpl implements Cell {
    private final List<BankNote> bankNoteList;

    public CellImpl(List<BankNote> bankNoteList) {
        this.bankNoteList = bankNoteList;
    }

    @Override
    public int getBalance() {
        return bankNoteList.stream().mapToInt(BankNote::getDenomination).sum();
    }

    @Override
    public void putBankNoteList(List<BankNote> bankNoteList) {
        this.bankNoteList.addAll(bankNoteList);
    }

    @Override
    public List<BankNote> deliveryBankNoteList(int count) {
        List<BankNote> removedBankNoteList = bankNoteList.subList(bankNoteList.size() - count, bankNoteList.size());
        bankNoteList.subList(bankNoteList.size() - count, bankNoteList.size()).clear();
        return removedBankNoteList;
    }

    @Override
    public List<BankNote> getCellBankNoteList() {
        return bankNoteList;
    }
}
