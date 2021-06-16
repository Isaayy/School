package com.example.demo.controller;

import com.example.demo.components.DocumentComponent;
import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.services.AnswersService;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

/**
 * This class is api controller
 */
@RestController
public class QuizzyApiController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private DocumentComponent documentComponent;

    // Read file name
    /**
     * This method is csv file reader
     * @param categoryDto is data transfer object of category data
     * @return HTTP status response OK
     * @throws IOException
     */
    @CrossOrigin
    @PostMapping(value = "/quiz/category")
    public ResponseEntity<?> readFile(@RequestBody CategoryDto categoryDto) throws IOException {

        questionService.readQuestions(categoryDto.getCategory());
        answersService.resetAnswers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This method is JSON creator
     * @param id This is number of question inside each category
     * @return JSON of data created for question by id number
     */
    @CrossOrigin
    @GetMapping("/quiz/question/{id}")
    public ResponseEntity<HashMap<String, Object>> getQuestion(@PathVariable("id") String id) {

        // Create returned JSON
        int index = Integer.parseInt(id);
        HashMap<String, Object> returnedData = new HashMap<>();
        returnedData.put("question", questionService.getQuestions().get(index).getQuestion());
        returnedData.put("answers", questionService.getQuestions().get(index).getAnswers());
        returnedData.put("points", questionService.getQuestions().get(index).getPoints());
        returnedData.put("lastQuestion",index == questionService.getQuestions().size() - 1);

        return ResponseEntity.ok(returnedData);
    }

    /**
     * This class read answer from client and check if selected answers equals correct answers, add yes/no to isCorrect array if yes increase user points
     * @param answer This is answer data from user
     * @return HTTP status response OK
     */
    @CrossOrigin
    @PutMapping("/quiz/calculate")
    public @ResponseBody ResponseEntity<Void> checkAnswer(@RequestBody AnswerDto answer) {

        // Add selected answer to answers array
        answersService.addAnswer(answer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This is pdf file with results creator
     * @return HTTP status response OK
     */
    @CrossOrigin
    @PostMapping(value = "/quiz/report")
    public @ResponseBody ResponseEntity<Void> generatePdf() {

        String directory = System.getProperty("user.home");

        documentComponent.createDocument(
                directory + "/Desktop/quiz_results.pdf",
                answersService.getUserPoints(),
                questionService.getMaxPoints() / 2,
                questionService.getMaxPoints(),
                questionService.getQuestions(),
                answersService.getUserAnswers(),
                answersService.getIsCorrect()
        );

        if (documentComponent.getDidUserPass()) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
