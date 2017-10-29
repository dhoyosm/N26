package me.danielhoyos.n26.services.impl;

import me.danielhoyos.n26.models.Statistics;
import me.danielhoyos.n26.models.Transaction;
import me.danielhoyos.n26.services.StatisticsService;
import me.danielhoyos.n26.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daniel Hoyos
 *
 */

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final static Long TIME_LIMIT = 60000L;

    @Autowired
    private TransactionService transactionService;

    @Override
    public Statistics getStatistics(Long currentTimestamp) {
        Statistics statistics = new Statistics();

        long lowerTimestamp = currentTimestamp - TIME_LIMIT;

        List<Transaction> transactions = transactionService.getLastMinuteTransactions(lowerTimestamp);

        if (!transactions.isEmpty()) {
            double sum = transactions.parallelStream()
                    .mapToDouble(Transaction::getAmount).sum();
            double avg = transactions.parallelStream()
                    .mapToDouble(Transaction::getAmount).average().getAsDouble();
            double max = transactions.parallelStream()
                    .mapToDouble(Transaction::getAmount).max().getAsDouble();
            double min = transactions.parallelStream()
                    .mapToDouble(Transaction::getAmount).min().getAsDouble();
            statistics.setSum(sum);
            statistics.setAvg(avg);
            statistics.setMax(max);
            statistics.setMin(min);
            statistics.setCount(transactions.size());
        }

        return statistics;
    }
}
