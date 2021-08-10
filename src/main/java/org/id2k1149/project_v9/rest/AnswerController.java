package org.id2k1149.project_v9.rest;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public List<Answer> getAnswers() {
        return answerService.getAnswers();
    }

    @GetMapping(path = "{id}")
    public Answer getAnswer(@PathVariable("id") Long id) {
        return answerService.getAnswer(id);
    }

    @PostMapping
    public void addAnswer(@RequestBody Answer newAnswer) {
        answerService.addAnswer(newAnswer);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(
            @RequestBody Answer answer,
            @PathVariable("id") Long id
    ) {
        answerService.updateAnswer(answer, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAnswer(@PathVariable("id") Long id) {
        answerService.deleteAnswer(id);
    }
}