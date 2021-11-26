package atm;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface CellStorage {
    Cell getCell(Integer key);

    TreeMap<Integer, Cell> getCellList();

    int getCellListBalance();

    void createCell(Map.Entry<Integer, Cell> cell);

    void loadBankNoteList(Integer denomination, List<BankNote> bankNoteList);

    List<BankNote> deliveryBankNoteList(Integer denomination, int count);
}
