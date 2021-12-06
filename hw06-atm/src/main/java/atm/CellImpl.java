package atm;

import java.util.List;
import java.util.stream.Collectors;

public class CellImpl implements Cell {
    private List<BankNote> bankNoteList;

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
        List<BankNote> removedBankNoteList = bankNoteList.stream().limit(count).collect(Collectors.toList());
        bankNoteList.removeAll(removedBankNoteList);
        return removedBankNoteList;
    }

    @Override
    public List<BankNote> getCellBankNoteList() {
        return bankNoteList;
    }
}
