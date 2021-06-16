package com.example.systemyKolejkowe.controller;

import com.example.systemyKolejkowe.dto.QueuingSystemDto;
import com.example.systemyKolejkowe.service.QueuingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class QueuingSystemApiController {

    @Autowired
    QueuingSystemService queuingSystemService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/system/get", method = RequestMethod.GET)
    public ResponseEntity<QueuingSystemDto> testDocument(@RequestParam(value = "lambda") float lambda,
                                             @RequestParam(value = "mu") float mu,
                                             @RequestParam(value = "m") int m) {

        QueuingSystemDto queuingSystemDto = queuingSystemService.getQueuingSystem(lambda, mu, m);

        return ResponseEntity.ok(queuingSystemDto);
    }
}
