package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is question data transfer object
 */
public class QuestionDto {

    private String id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int[] correct;
    private int points;

    /**
     * This is constructor of DTO object with question data
     * @param arr Array with question data
     */
    public QuestionDto(String[] arr) {
        this.id = arr[0];
        this.question = arr[1];
        this.answer1 = arr[2];
        this.answer2 = arr[3];
        this.answer3 = arr[4];
        this.answer4 = arr[5];

        if (!arr[6].equals("CORRECT_ANSWERS")){
            this.correct = Arrays.stream(arr[6].split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
        }

        if (!arr[7].equals("POINTS")){
            this.points = Integer.parseInt(arr[7]);
        }

    }

    /**
     * This method is available answers getter
     * @return List of four answers to the question
     */
    public List<String> getAnswers() {

            List<String> ans = new ArrayList<>();
            ans.add(this.answer1);
            ans.add(this.answer2);
            ans.add(this.answer3);
            ans.add(this.answer4);

        return ans;
    }

    /**
     * This is question id getter
     * @return String with question id
     */
    public String getId() {
        return id;
    }

    /**
     * This is question text getter
     * @return String with question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * This is first answer getter
     * @return String with first answer
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * This is second answer getter
     * @return String with second answer
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * This is third answer getter
     * @return String with third answer
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * This is fourth answer getter
     * @return String with fourth answer
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * This is correct answers getter
     * @return array of correct answers
     */
    public int[] getCorrect() {
        return correct;
    }

    /**
     * This is points getter
     * @return amount of points for correct answer
     */
    public int getPoints() {
        return points;
    }
}
