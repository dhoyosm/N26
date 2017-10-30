package me.danielhoyos.n26.services.impl;

import me.danielhoyos.n26.N26Application;
import me.danielhoyos.n26.models.Statistics;
import me.danielhoyos.n26.models.Transaction;
import me.danielhoyos.n26.services.TransactionService;
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
import static org.mockito.Matchers.anyLong;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author Daniel Hoyos
 *
 */
@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = N26Application.class)
@PrepareForTest(StatisticsServiceImplTests.class)
@SpringBootTest
public class StatisticsServiceImplTests {

    @Mock
    TransactionService transactionService;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testGetStatistics() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(2.0, 2L));
        transactions.add(new Transaction(5.0, 5L));
        transactions.add(new Transaction(3.0, 3L));
        transactions.add(new Transaction(1.0, 1L));
        transactions.add(new Transaction(4.0, 4L));

        when(transactionService.getLastMinuteTransactions(anyLong())).thenReturn(transactions);

        Statistics result = statisticsService.getStatistics(1L);

        assertNotNull(result);
        assertEquals(new Double(15.0), new Double(result.getSum()));
        assertEquals(new Double(3.0), new Double(result.getAvg()));
        assertEquals(new Double(1.0), new Double(result.getMin()));
        assertEquals(new Double(5.0), new Double(result.getMax()));
        assertEquals(new Long(5), new Long(result.getCount()));
    }

    @Test
    public void testGetStatisticsNoTransactions() {
        when(transactionService.getLastMinuteTransactions(anyLong())).thenReturn(new ArrayList<>());

        Statistics result = statisticsService.getStatistics(1L);

        assertNotNull(result);
        assertEquals(new Double(0), new Double(result.getSum()));
        assertEquals(new Double(0), new Double(result.getAvg()));
        assertEquals(new Double(0), new Double(result.getMin()));
        assertEquals(new Double(0), new Double(result.getMax()));
        assertEquals(new Long(0), new Long(result.getCount()));
    }
}
