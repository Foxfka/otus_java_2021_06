package atm;

import java.util.*;
import java.util.stream.Collectors;

public class ATMImpl implements ATM {

    private final CellStorage cellStorage;

    public ATMImpl(CellStorage cellStorage) {
        this.cellStorage = cellStorage;
        ;
    }

    @Override
    public void createCell(Map.Entry<Integer, Cell> cell) {
        cellStorage.createCell(cell);
    }

    @Override
    public int getAtmBalance() {
        return cellStorage.getCellListBalance();
    }

    @Override
    public void loadingMoney(List<BankNote> bankNoteList) {
        Map<Integer, List<BankNote>> banknoteListMap = bankNoteList.stream().collect(Collectors.groupingBy(BankNote::getDenomination));

        banknoteListMap.keySet().stream().forEach(key -> {
            if (cellStorage.getCell(key) != null) {
                cellStorage.loadBankNoteList(key, banknoteListMap.get(key));
            } else {
                throw new UnknownDenominationException("Похоже вы пытаетесь загрузить листик вместо банкноты");
            }
        });
    }

    @Override
    public List<BankNote> deliveryMoney(int sum) {
        if (sum > getAtmBalance()) {
            throw new NotEnoughFundsException("В банкомате Недостаточно средств для выполнения операции");
        }

        var collectedSum = 0;

        var bankNoteList = new ArrayList<BankNote>();
        var banknoteCountByCellMap = new HashMap<Integer, Integer>();

        for (var cell : cellStorage.getCellList().entrySet()) {
            var cellDenomination = cell.getKey();

            var cellBankNoteCount = cell.getValue().getCellBankNoteList().size();

            var banknoteToDeliveryCount = (sum - collectedSum) / cellDenomination;

            if (banknoteToDeliveryCount > cellBankNoteCount) {
                banknoteToDeliveryCount = cellBankNoteCount;
            }

            if (banknoteToDeliveryCount > 0) {
                collectedSum += banknoteToDeliveryCount * cellDenomination;
                banknoteCountByCellMap.put(cellDenomination, banknoteToDeliveryCount);
            }

            if (collectedSum == sum) {
                break;
            }
        }

        if (collectedSum < sum) {
            throw new NotHaveBankNotesException("Нет возможности набрать запрошенную сумму");
        }

        banknoteCountByCellMap.entrySet().stream()
                .forEach((cell) -> bankNoteList.addAll(cellStorage.deliveryBankNoteList(cell.getKey(), cell.getValue())));

        return bankNoteList;
    }
}
