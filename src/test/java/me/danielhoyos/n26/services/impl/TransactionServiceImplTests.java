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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
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
        //mockStatic(System.class);
    }

    @Test
    public void testCreateGame() {
        Transaction input = new Transaction(10.2, 1234L);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(input);

        Transaction result = transactionService.saveTransaction(input);

        assertNotNull(result);

        assertEquals(new Double(10.2), result.getAmount());
        assertEquals(new Long(1234L), result.getTimestamp());
    }
}
