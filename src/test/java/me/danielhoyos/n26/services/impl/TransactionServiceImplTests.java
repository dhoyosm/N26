package me.danielhoyos.n26.services.impl;

import me.danielhoyos.n26.N26Application;
import me.danielhoyos.n26.models.Transaction;
import me.danielhoyos.n26.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author Daniel Hoyos
 *
 */
@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = N26Application.class)
@PrepareForTest(TransactionServiceImplTests.class)
@SpringBootTest
public class TransactionServiceImplTests {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testCreateTransaction() {
        Transaction input = new Transaction(10.2, 1234L);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(input);

        Transaction result = transactionService.saveTransaction(input);

        assertNotNull(result);

        assertEquals(new Double(10.2), result.getAmount());
        assertEquals(new Long(1234L), result.getTimestamp());
    }

    @Test
    public void testGetTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactions();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetLastMinuteTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());

        when(transactionRepository.findByTimestampGreaterThanEqual(anyLong())).thenReturn(transactions);

        List<Transaction> result = transactionService.getLastMinuteTransactions(new Long(0));

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, 1);
    }
}
