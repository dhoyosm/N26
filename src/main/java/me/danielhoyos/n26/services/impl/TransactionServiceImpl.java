package me.danielhoyos.n26.services.impl;

import me.danielhoyos.n26.models.Transaction;
import me.danielhoyos.n26.repositories.TransactionRepository;
import me.danielhoyos.n26.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daniel Hoyos
 *
 */

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getLastMinuteTransactions(Long lowerTimestamp) {
        return transactionRepository.findByTimestampGreaterThanEqual(lowerTimestamp);
    }
}
