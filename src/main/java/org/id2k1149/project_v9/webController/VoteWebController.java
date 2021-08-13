package org.id2k1149.project_v9.webController;

import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.service.AnswerService;
import org.id2k1149.project_v9.service.CounterService;
import org.id2k1149.project_v9.service.InfoService;
import org.id2k1149.project_v9.service.VoterService;
import org.id2k1149.project_v9.to.AnswerTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class VoteWebController {

    private final InfoService infoService;
    private final CounterService counterService;
    private final AnswerService answerService;
    private final VoterService voterService;

    public VoteWebController(InfoService infoService,
                             CounterService counterService,
                             AnswerService answerService,
                             VoterService voterService) {

        this.infoService = infoService;
        this.counterService = counterService;
        this.answerService = answerService;
        this.voterService = voterService;
    }


    @GetMapping("/vote")
    public String survey(Model model) {
        List<AnswerTo> answersList = infoService.checkTime();
        if (answersList.size() > 0) {
            model.addAttribute("answersList", answersList);
            Optional<Voter> optionalVoter = voterService.checkUser();
            if (optionalVoter.isPresent()) model.addAttribute("error1", "Your voted today.");
        } else {
            model.addAttribute("error2", "It is too late to vote.");
        }
        return "vote";
    }

    @PostMapping("/vote")
    public String vote(VotesCounter votesCounter) {
        if (votesCounter.getAnswer() == null) {
            return "redirect:/vote";
        }
        counterService.vote(votesCounter);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(Model model) {
        List<VotesCounter> sortedList = counterService.getResult();
        if (sortedList.size() > 0) {
            model.addAttribute("sortedList", sortedList);
        } else {
            model.addAttribute("error", "There is no result");
        }

        return "result";
    }
}
