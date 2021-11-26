package atm;

import java.util.List;
import java.util.Map;

public interface ATM {
    int getAtmBalance();

    void loadingMoney(List<BankNote> bankNoteList);

    void createCell(Map.Entry<Integer, Cell> cell);

    List<BankNote> deliveryMoney(int sum);
}
