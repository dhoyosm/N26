package me.danielhoyos.n26.controllers;

import me.danielhoyos.n26.models.Transaction;
import me.danielhoyos.n26.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Hoyos
 *
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final long TIME_LIMIT = 60000L;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction) {

        HttpStatus httpStatus = HttpStatus.NO_CONTENT;

        long currentTimestamp = System.currentTimeMillis();

        if (TIME_LIMIT > (currentTimestamp - transaction.getTimestamp())) {
            transactionService.saveTransaction(transaction);
            httpStatus = HttpStatus.CREATED;
        }

        return new ResponseEntity<>("", httpStatus);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> retrieveTransactions() {

        List<Transaction> transactions = transactionService.getTransactions();

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
