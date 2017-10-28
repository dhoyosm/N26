package me.danielhoyos.n26.repositories;

import me.danielhoyos.n26.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Daniel Hoyos
 *
 */

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
