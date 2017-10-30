package me.danielhoyos.n26.services;

import me.danielhoyos.n26.models.Transaction;

import java.util.List;

/**
 * @author Daniel Hoyos
 *
 */

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    List<Transaction> getLastMinuteTransactions(Long lowerTimestamp);
}
