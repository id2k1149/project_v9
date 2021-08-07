package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionWebController {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final InfoRepository infoRepository;

    @Autowired
    public QuestionWebController(QuestionRepository questionRepository,
                                 AnswerRepository answerRepository,
                                 InfoRepository infoRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.infoRepository = infoRepository;
    }

    @GetMapping("/questions")
    public String questions(Model model) {
        List<Question> questionsList = questionRepository.findAll();
        model.addAttribute("questionsList", questionsList);
        return "WEB-INF/jsp/questions";
    }

    @GetMapping("/today")
    public String questionsForYou(Model model) {
        List<Question> questionsList = questionRepository.findByDatePublished(LocalDate.now());
        model.addAttribute("questionsList", questionsList);

//        if (LocalDateTime.now().getHour() < 23) {
//
//            model.addAttribute("questionsList", questionsList);
//            return "WEB-INF/jsp/today";
//        } else {
//            re
//        }
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


}
