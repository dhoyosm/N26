package me.danielhoyos.n26.services;

import me.danielhoyos.n26.models.Statistics;

/**
 * @author Daniel Hoyos
 *
 */

public interface StatisticsService {
    Statistics getStatistics(Long currentTimestamp);
}
