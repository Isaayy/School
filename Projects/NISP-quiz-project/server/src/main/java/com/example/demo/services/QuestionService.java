package com.example.demo.services;

import com.example.demo.dto.QuestionDto;

import java.io.IOException;
import java.util.List;

/**
 * This interface operate with questions data
 */
public interface QuestionService {

    void readQuestions(String categoryName) throws IOException;

    List<QuestionDto> getQuestions();

    int getMaxPoints();

}
