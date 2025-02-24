import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final double INITIAL_BALANCE = 0;
    private static final double HUNDRED_EUROS = 100;
    private static final double SEVENTY_EUROS = 70;
    private static final int WRONG_ID = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), HUNDRED_EUROS);
        assertEquals(HUNDRED_EUROS, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), HUNDRED_EUROS);
        bankAccount.deposit(WRONG_ID, HUNDRED_EUROS);
        assertEquals(HUNDRED_EUROS, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), HUNDRED_EUROS);
        bankAccount.withdraw(accountHolder.getId(), SEVENTY_EUROS);
        assertEquals(HUNDRED_EUROS-SEVENTY_EUROS, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), HUNDRED_EUROS);
        bankAccount.withdraw(WRONG_ID, SEVENTY_EUROS);
        assertEquals(HUNDRED_EUROS, bankAccount.getBalance());
    }

    @Test
    void testDepositNegativeAmount() {
        bankAccount.deposit(accountHolder.getId(), HUNDRED_EUROS);
        bankAccount.deposit(accountHolder.getId(), -SEVENTY_EUROS);
        assertEquals(HUNDRED_EUROS, bankAccount.getBalance());
    }

    @Test
    void testWithdrawNegativeAmount() {
        bankAccount.deposit(accountHolder.getId(), HUNDRED_EUROS);
        bankAccount.withdraw(accountHolder.getId(), -SEVENTY_EUROS);
        assertEquals(HUNDRED_EUROS, bankAccount.getBalance());
    }
}
