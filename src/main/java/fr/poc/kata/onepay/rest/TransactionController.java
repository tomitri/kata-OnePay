package fr.poc.kata.onepay.rest;

import fr.poc.kata.onepay.domain.enums.PaymentStatus;
import fr.poc.kata.onepay.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.poc.kata.onepay.domain.Transaction;

import java.util.List;

@RestController
@RequestMapping("${api.base.url}")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Operation(description = "create a new transaction")
    @PostMapping(value = "/createTransaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transactionValue) {
        Transaction transaction = transactionService.createTransaction(transactionValue);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Operation(description = "update or modify the status of a transaction")
    @PutMapping(value = "/updateTransaction/{id}/status")
    public ResponseEntity<Transaction> updateTransactionStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
        Transaction transaction = transactionService.updateTransactionStatus(id, status);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Operation(description = "recover all transactions")
    @GetMapping(value = "/allTransactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

}
