package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.id2k1149.project_v9.repository.VotesCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class QuestionWebController {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final InfoRepository infoRepository;

    private final VotesCounterRepository votesCounterRepository;

    @Autowired
    public QuestionWebController(QuestionRepository questionRepository,
                                 AnswerRepository answerRepository,
                                 InfoRepository infoRepository,
                                 VotesCounterRepository votesCounterRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.infoRepository = infoRepository;
        this.votesCounterRepository = votesCounterRepository;
    }

    @GetMapping("/questions")
    public String questions(Model model) {
        List<Question> questionsList = questionRepository.findAll();
        model.addAttribute("questionsList", questionsList);
        return "WEB-INF/jsp/questions";
    }

    @GetMapping("/today")
    public String questionsForYou(Model model) {
        List<Question> questionsList = new ArrayList<>();

        if (LocalDateTime.now().getHour() < 23) {
            questionsList = questionRepository.findByDatePublished(LocalDate.now());
        }
        model.addAttribute("questionsList", questionsList);
        return "WEB-INF/jsp/today";
    }

//    @GetMapping("/new")
//    public String newQuestionForm(Model model) {
//        List<Answer> answersList = answerRepository.findAll();
//        model.addAttribute("answersList", answersList);
//        model.addAttribute("question", new Question());
//
//        return "WEB-INF/jsp/questionForm";
//    }

    @GetMapping("/confirmation")
    public String confirmation(){
        List<Question> questionsList = questionRepository.findByDatePublished(LocalDate.now());
        System.out.println("questionsList" + questionsList.size());
        if (questionsList.size() > 0) {
            return "WEB-INF/jsp/confirmation";
        } else {
            return "WEB-INF/jsp/questionForm";
        }
    }

    @GetMapping("/new")
    public String newQuestionForm(Model model) {

        model.addAttribute("question", new Question());
        List<Info> infoList = infoRepository.findByDateOfInfo(LocalDate.now());
        List<Answer> answersList = new ArrayList<>();
        for (Info info : infoList) {
            answersList.add(info.getAnswer());
        }
        model.addAttribute("answersList", answersList);

        return "WEB-INF/jsp/questionForm";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(Question question) {
        questionRepository.save(question);
        return "redirect:/questions";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        String error_message = null;
        boolean voted = false;
//        boolean voted = true;

        if (voted) {
            error_message = "You already voted";
            model.addAttribute("error_message", error_message);

        } else {
            Question question = questionRepository.findById((long) id).get();
            model.addAttribute("question", question);
            Set<Answer> answersList = question.getAnswers();
            model.addAttribute("answersList", answersList);

        }
        return "WEB-INF/jsp/show";
    }

    @PostMapping("/vote/{id}")
    public String vote(@PathVariable("id") int id, VotesCounter votesCounter) {
        VotesCounter newVote = new VotesCounter();
        Answer answer = votesCounter.getAnswer();
        Question question = questionRepository.findById((long) id).get();
        int votes = 0;

        Optional<VotesCounter> optionalVotesCounter = votesCounterRepository.findByQuestionAndAnswer(question, answer);
        if (optionalVotesCounter.isPresent())  {
            newVote = optionalVotesCounter.get();
            votes = newVote.getVotes();
        }

        votes += 1;
        newVote.setQuestion(question);
        newVote.setAnswer(answer);
        newVote.setVotes(votes);
        votesCounterRepository.save(newVote);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result() {

        return "WEB-INF/jsp/result";

    }


}
