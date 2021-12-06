package atm;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATMImpl(new CellStorageImpl());
        System.out.println("Current atm balance is: " + atm.getAtmBalance());
    }
}
