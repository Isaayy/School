package com.example.demo.services.impl;

import com.example.demo.dto.AnswerDto;
import com.example.demo.services.AnswersService;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is implementation of AnswersService interface
 */
@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private QuestionService questionService;

    private List<List<String>> userAnswers = new ArrayList<>();

    private List<String> isCorrect = new ArrayList<>();

    private int userPoints = 0;

    /**
     * This class read answers from and check if selected answers equals correct answers, add yes/no to isCorrect array if yes increase user points
     */
    @Override
    public void addAnswer(AnswerDto answerDto) {

        List<String> selectedAnswers = new ArrayList<>();
        for (int k = 0 ; k < answerDto.getSelectedAnswers().length ; k++)
            selectedAnswers.add(questionService.getQuestions().get(answerDto.getQuestionId()).getAnswers().get(answerDto.getSelectedAnswers()[k]-1));
        userAnswers.add(selectedAnswers);

        if (Arrays.equals(questionService.getQuestions().get(answerDto.getQuestionId()).getCorrect(), answerDto.getSelectedAnswers())){
            userPoints += questionService.getQuestions().get(answerDto.getQuestionId()).getPoints();
            isCorrect.add("Yes");
        }
        else isCorrect.add("No");
    }


    /**
     * This method is getter of user answers
     * @return User answers
     */
    public List<List<String>> getUserAnswers() {
        return userAnswers;
    }

    /**
     * This method is getter of user points
     * @return User points
     */
    public int getUserPoints() {
        return userPoints;
    }

    /**
     * This method is getter of list of correct answers
     * @return List of correct answers
     */
    public List<String> getIsCorrect() {
        return isCorrect;
    }

    /**
     * This method resets answers and points
     */
    public void resetAnswers() {
        userAnswers = new ArrayList<>();
        isCorrect = new ArrayList<>();
        userPoints = 0 ;
    }
}
