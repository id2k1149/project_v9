package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuestionWebController {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    @Autowired
    public QuestionWebController(QuestionRepository questionRepository,
                                 AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("/questions")
    public String questions(Model model) {
        List<Question> questionsList = questionRepository.findAll();
        model.addAttribute("questionsList", questionsList);
        return "WEB-INF/jsp/questions";
    }

    @GetMapping("/new")
    public String newQuestionForm(Model model) {
        List<Answer> answersList = answerRepository.findAll();
        model.addAttribute("answersList", answersList);
        model.addAttribute("question", new Question());

        return "WEB-INF/jsp/questionForm";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(Question question) {
        questionRepository.save(question);
        return "redirect:/questions";

    }


}
