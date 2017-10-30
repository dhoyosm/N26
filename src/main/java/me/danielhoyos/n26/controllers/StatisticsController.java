package me.danielhoyos.n26.controllers;

import me.danielhoyos.n26.models.Statistics;
import me.danielhoyos.n26.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Hoyos
 *
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Statistics> getStatistics() {

        Statistics statistics = statisticsService.getStatistics(System.currentTimeMillis());

        return new ResponseEntity<>(statistics, HttpStatus.OK);

    }
}
