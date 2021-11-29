import atm.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ATMImplTest {
    ATM atm = new ATMImpl(new CellStorageImpl());

    @BeforeEach
    public void configATM() {
        List<BankNote> banknotes_100 = Arrays.stream(new BankNote[1])
                .map(bankNote -> new BankNoteImpl(100))
                .collect(Collectors.toList());

        List<BankNote> banknotes_1000 = Arrays.stream(new BankNote[1])
                .map(bankNote -> new BankNoteImpl(1000))
                .collect(Collectors.toList());

        List<BankNote> banknotes_500 = Arrays.stream(new BankNote[1])
                .map(bankNote -> new BankNoteImpl(500))
                .collect(Collectors.toList());

        atm.createCell(new AbstractMap.SimpleEntry<>(100, new CellImpl(banknotes_100)));
        atm.createCell(new AbstractMap.SimpleEntry<>(1000, new CellImpl(banknotes_1000)));
        atm.createCell(new AbstractMap.SimpleEntry<>(500, new CellImpl(banknotes_500)));
    }

    @Test
    @DisplayName("должен принимать банкноты и корректно изменять баланс")
    public void shouldTakeBankNoteListAndHasCorrectBalance() {
        atm.loadingMoney(List.of(new BankNoteImpl(1000), new BankNoteImpl(100), new BankNoteImpl(500)));
        Assertions.assertEquals(3200, atm.getAtmBalance());
    }

    @Test
    @DisplayName("должен выкидывать ошибку при попытке загрзуить банкноту, под которую нет ячейки")
    public void shouldThrowUnknowDenominationError() {
        UnknownDenominationException thrown = Assertions.assertThrows(UnknownDenominationException.class, () -> {
            atm.loadingMoney(List.of(new BankNoteImpl(50)));
        });

        Assertions.assertEquals("Похоже вы пытаетесь загрузить листик вместо банкноты", thrown.getMessage());
    }

    @Test
    @DisplayName("должен выкидывать ошибку при попытке снять больше денег, чем есть в банкомате")
    public void shouldThrowNotEnoughFundsException() {
        NotEnoughFundsException thrown = Assertions.assertThrows(NotEnoughFundsException.class, () -> {
            atm.deliveryMoney(1700);
        });

        Assertions.assertEquals("В банкомате Недостаточно средств для выполнения операции", thrown.getMessage());
    }

    @Test
    @DisplayName("должен корректно изменять баланс банкомата при выдаче наличных")
    public void shouldReduceBalanceWhenIssuingCash() {
        atm.deliveryMoney(1000);

        Assertions.assertEquals(600, atm.getAtmBalance());
    }

    @Test
    @DisplayName("должен выкидывать ошибку при попытке снять сумму, которую невозможно набрать имеющимися купюрами")
    public void shouldThrowNotHaveBankNotesException() {
        NotHaveBankNotesException thrown = Assertions.assertThrows(NotHaveBankNotesException.class, () -> {
            atm.deliveryMoney(1200);
        });

        Assertions.assertEquals("Нет возможности набрать запрошенную сумму", thrown.getMessage());
    }
}
