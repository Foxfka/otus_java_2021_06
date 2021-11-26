package atm;

import java.util.List;

public interface Cell {

    int getBalance();

    void putBankNoteList(List<BankNote> bankNoteList);

    List<BankNote> deliveryBankNoteList(int count);

    List<BankNote> getCellBankNoteList();
}
