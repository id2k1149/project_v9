package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminWebController {

    private final QuestionRepository questionRepository;
    private final InfoRepository infoRepository;

    @Autowired
    public AdminWebController(QuestionRepository questionRepository,
                              InfoRepository infoRepository
                              ) {
        this.questionRepository = questionRepository;
        this.infoRepository = infoRepository;

    }

    @GetMapping("admin/questions")
    public String questions(Model model) {
        List<Question> questionsList = questionRepository.findAll();
        model.addAttribute("questionsList", questionsList);
        return "admin/questions";
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

        return "admin/questionForm";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(Question question) {
        questionRepository.save(question);
        return "redirect:/admin/questions";
    }

}
