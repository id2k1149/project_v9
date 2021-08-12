package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.service.CounterService;
import org.id2k1149.project_v9.service.InfoService;
import org.id2k1149.project_v9.to.AnswerTo;
import org.id2k1149.project_v9.util.AnswerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VoteWebController {

    private final InfoService infoService;
    private final CounterService counterService;

    public VoteWebController(InfoService infoService,
                             CounterService counterService) {

        this.infoService = infoService;
        this.counterService = counterService;
    }


    @GetMapping("/vote")
    public String survey(Model model) {
        List<AnswerTo> answersList = AnswerUtil
                .getAnswersTo(infoService.getTodayAnswersInfo(),
                        infoService.getByDate(LocalDate.now()));

        model.addAttribute("answersList", answersList);
        return "vote";
    }

    @PostMapping("/vote")
    public String vote(VotesCounter votesCounter) {
        counterService.vote(votesCounter);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(Model model) {
        List<VotesCounter> sortedList = counterService.getResult();
        model.addAttribute("sortedList", sortedList);
        return "result";
    }
}
