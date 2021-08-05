package org.id2k1149.project_v9.rest;

import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping(path = "{questionId}")
    public Question getQuestion(@PathVariable("questionId") Long questionId) {
        return questionService.getQuestion(questionId);
    }

    @PostMapping
    public void addQuestion(@RequestBody Question newQuestion) {
        questionService.addQuestion(newQuestion);
    }
}
