package atm;

import java.util.*;

public class CellStorageImpl implements CellStorage{
    private TreeMap<Integer, Cell> cellList;

    public CellStorageImpl() {
        this.cellList = new TreeMap<Integer, Cell>(Collections.reverseOrder());
    }

    @Override
    public Cell getCell(Integer key) {
        return cellList.get(key);
    }

    @Override
    public int getCellListBalance() {
        return cellList.values().stream().mapToInt(Cell::getBalance).sum();
    }

    @Override
    public TreeMap<Integer, Cell> getCellList() {
        return cellList;
    }

    @Override
    public void createCell(Map.Entry<Integer, Cell> cell) {
        cellList.put(cell.getKey(), cell.getValue());
    }

    @Override
    public void loadBankNoteList(Integer denomination, List<BankNote> bankNoteList) {
        cellList.get(denomination).putBankNoteList(bankNoteList);
    }

    @Override
    public List<BankNote> deliveryBankNoteList(Integer denomination, int count) {
        return cellList.get(denomination).deliveryBankNoteList(count);
    }
}
