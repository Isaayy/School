package com.example.demo.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AverageApiController {

    private static final Logger LOG = LoggerFactory.getLogger(AverageApiController.class);

    @GetMapping("/api/math/digits/{digits}")
    public ResponseEntity<Map<String, Double>> getAverage(@PathVariable("digits") String digits) {
        LOG.info("*** digits inside server: {}",digits);
        return ResponseEntity.ok(calculateAverage(digits));
    }

    private Map<String, Double> calculateAverage(String digits){
        String[] digitsAsArraysOfString = digits.split(",");
        double sumElements = 0.0;

        for(String digit: digitsAsArraysOfString){
            sumElements += Double.valueOf(digit);
        }
        Map<String,Double> result = new HashMap<>();
        result.put("average", sumElements / digitsAsArraysOfString.length);

        return result;
    }
}
