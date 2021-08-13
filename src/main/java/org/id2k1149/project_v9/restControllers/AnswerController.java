package org.id2k1149.project_v9.restControllers;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.service.AnswerService;
import org.id2k1149.project_v9.service.InfoService;
import org.id2k1149.project_v9.to.AnswerTo;
import org.id2k1149.project_v9.util.AnswerUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final InfoService infoService;

    public AnswerController(AnswerService answerService,
                            InfoService infoService) {
        this.answerService = answerService;
        this.infoService = infoService;
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
    public void updateAnswer(
            @RequestBody Answer answer,
            @PathVariable("id") Long id
    ) {
        answerService.updateAnswer(answer, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAnswer(@PathVariable("id") Long id) {
        answerService.deleteAnswer(id);
    }

    @GetMapping("/today")
    public List<AnswerTo> getTodayInfo() {
        return AnswerUtil.getAnswersTo(infoService.getTodayAnswersInfo(), infoService.getByDate(LocalDate.now()));
    }

    @GetMapping(path = "{id}/info")
    public AnswerTo getAllInfoForAnswer(@PathVariable("id") Long id) {
        return answerService.getAllInfoForAnswer(id);
    }

}