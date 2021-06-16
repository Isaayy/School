package com.example.systemyKolejkowe.service.impl;

import com.example.systemyKolejkowe.dto.QueuingSystemDto;
import com.example.systemyKolejkowe.model.QueuingSystem;
import com.example.systemyKolejkowe.service.QueuingSystemService;
import org.springframework.stereotype.Service;

@Service
public class QueuingSystemServiceImpl implements QueuingSystemService {

    @Override
    public QueuingSystemDto getQueuingSystem(float lambda, float mu, int m) {

        QueuingSystem queuingSystem = new QueuingSystem(lambda, mu, m);

        return new QueuingSystemDto(
                queuingSystem.getProbability0(), queuingSystem.getProbability(), queuingSystem.getAverageV(), queuingSystem.getAverageN(),
                queuingSystem.getAverageM0(), queuingSystem.getAverageTt(), queuingSystem.getAverageTs(),
                queuingSystem.getAverageMnz(), queuingSystem.getError()
        );
    }
}
