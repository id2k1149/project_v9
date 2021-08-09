package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.id2k1149.project_v9.repository.VotesCounterRepository;
import org.id2k1149.project_v9.service.MultiService;
import org.id2k1149.project_v9.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class QuestionWebController {

    private final QuestionRepository questionRepository;
    private final VotesCounterRepository votesCounterRepository;
    private final MultiService multiService;
    private final QuestionService questionService;

    @Autowired
    public QuestionWebController(QuestionRepository questionRepository,
                                 VotesCounterRepository votesCounterRepository,
                                 MultiService multiService,
                                 QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.votesCounterRepository = votesCounterRepository;
        this.multiService = multiService;
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public String questionsForUser(Model model) {
        List<Question> questionsList = questionService.getQuestionsForUser();
        model.addAttribute("questionsList", questionsList);
        return "questions";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Question question = questionService.getQuestion((long)id);
        model.addAttribute("question", question);

        Set<Answer> answersList = question.getAnswers();
        model.addAttribute("answersList", answersList);
        return "question";
    }

    @PostMapping("/vote/{id}")
    public String vote(@PathVariable("id") int id, VotesCounter votesCounter) {
        multiService.vote(id, votesCounter);
        return "redirect:/result/{id}";
    }

    @GetMapping("/result/{id}")
    public String result(@PathVariable("id") int id, Model model) {
        Question question = questionRepository.findById((long) id).get();
        List<VotesCounter> votesCounterList = votesCounterRepository.findByQuestion(question);

        List<VotesCounter> sortedList = votesCounterList.stream()
                .sorted(Comparator.comparingInt(VotesCounter::getVotes).reversed())
                .collect(Collectors.toList());

        VotesCounter bestResult = sortedList
                .stream()
                .max(Comparator.comparing(VotesCounter::getVotes))
                .orElseThrow(NoSuchElementException::new);

        int maxVotes = bestResult.getVotes();
        String result = bestResult.getAnswer().toString();
        question.setResult(result);
        questionRepository.save(question);

        model.addAttribute("sortedList", sortedList);
        model.addAttribute("question", question);
        model.addAttribute("maxVotes", maxVotes);

        return "result";
    }
}
