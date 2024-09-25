package fr.poc.kata.onepay.services;

import fr.poc.kata.onepay.PocKataOnePay;
import fr.poc.kata.onepay.domain.*;
import fr.poc.kata.onepay.domain.enums.PaymentStatus;
import fr.poc.kata.onepay.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PocKataOnePay.class)
public class TransactionServiceTest {

    private Transaction transactionV;
    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        transactionV = new Transaction();
        transactionV.setPaymentType(PaymentType.CREDIT_CARD);
        transactionV.setItems(Arrays.asList(new Item("T-shirt", 19.99, 5), new Item("Pants", 20.00, 2)));

    }

    @Test
    public void createTransaction() {
        Transaction transaction = transactionService.createTransaction(transactionV);
        assertEquals(PaymentStatus.NEW, transaction.getPaymentStatus());
    }

    @Test
    public void modifyTransaction() {
        Transaction transaction = transactionService.createTransaction(transactionV);
        assertEquals(PaymentStatus.NEW, transaction.getPaymentStatus());
    }

    @Test
    public void recoverAllTransactions() {
        Transaction transaction = transactionService.createTransaction(transactionV);
        assertEquals(PaymentStatus.NEW, transaction.getPaymentStatus());
    }

    @Test
    public void testTransactionLifecycle() {
        // Create a transaction
        Transaction transaction = new Transaction();
        transaction.setPaymentType(PaymentType.CREDIT_CARD);
        transaction.setItems(Arrays.asList(new Item("T-shirt", 19.99, 5), new Item("Pants", 20.00, 2)));
        transaction = transactionService.createTransaction(transaction);
        assertEquals(PaymentStatus.NEW, transaction.getPaymentStatus());

        // Authorize the transaction
        transaction = transactionService.updateTransactionStatus(transaction.getId(), PaymentStatus.AUTHORIZED);
        assertEquals(PaymentStatus.AUTHORIZED, transaction.getPaymentStatus());

        // Capture the transaction
        transaction = transactionService.updateTransactionStatus(transaction.getId(), PaymentStatus.CAPTURED);
        assertEquals(PaymentStatus.CAPTURED, transaction.getPaymentStatus());

        // Create another transaction
        Transaction transaction2 = new Transaction();
        transaction2.setPaymentType(PaymentType.PAYPAL);
        transaction2.setItems(Arrays.asList(new Item("Bike", 208.00, 1), new Item("Shoes", 30.00, 1)));
        transaction2 = transactionService.createTransaction(transaction2);
        assertEquals(PaymentStatus.NEW, transaction2.getPaymentStatus());

        // Cancel the transaction
        transaction2 = transactionService.updateTransactionStatus(transaction2.getId(), PaymentStatus.CANCELED);
        assertEquals(PaymentStatus.CANCELED, transaction2.getPaymentStatus());

        // Retrieve all transactions
        assertEquals(2, transactionService.getAllTransactions().size());
    }
}