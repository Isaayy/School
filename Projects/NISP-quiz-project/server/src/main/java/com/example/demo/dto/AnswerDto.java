package com.example.demo.dto;

/**
 * This class is answer data transfer object
 */
public class AnswerDto {
    private int questionId;
    private int[] selectedAnswers;
    private boolean lastQuestion;

    /**
     * This method question id number getter
     * @return Question id
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * This method is getter of selected answers by user
     * @return Selected answers
     */
    public int[] getSelectedAnswers() {
        return selectedAnswers;
    }

    /**
     * This method check if question is last in current quiz session
     * @return True or false
     */
    public boolean isLastQuestion() {
        return lastQuestion;
    }
}
