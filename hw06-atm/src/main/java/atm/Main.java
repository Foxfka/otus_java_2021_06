package atm;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATMImpl(new CellStorageImpl());

        List<BankNote> banknotes_100 = Arrays.stream(new BankNote[2])
                .map(bankNote -> new BankNoteImpl(100))
                .collect(Collectors.toList());

//        List<BankNote> banknotes_1000 = Arrays.stream(new BankNote[1])
//                .map(bankNote -> new BankNoteImpl(1000))
//                .collect(Collectors.toList());

        List<BankNote> banknotes_500 = Arrays.stream(new BankNote[10])
                .map(bankNote -> new BankNoteImpl(500))
                .collect(Collectors.toList());

        atm.createCell(new AbstractMap.SimpleEntry<>(100, new CellImpl(banknotes_100)));
//        atm.createCell(new AbstractMap.SimpleEntry<>(1000, new CellImpl(banknotes_1000)));
        atm.createCell(new AbstractMap.SimpleEntry<>(500, new CellImpl(banknotes_500)));

        System.out.println("Current atm balance is: " + atm.getAtmBalance());

        atm.deliveryMoney(600);

        System.out.println("Current atm balance is " + atm.getAtmBalance());

    }
}
