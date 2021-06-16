package com.example.systemyKolejkowe.service;

import com.example.systemyKolejkowe.dto.QueuingSystemDto;

public interface QueuingSystemService {

    QueuingSystemDto getQueuingSystem(float lambda, float mu, int m);
}
