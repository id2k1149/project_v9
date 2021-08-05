package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionWebController {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionWebController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping({"/questions"})
    public String questions(Model model) {
        return "WEB-INF/jsp/questions";
    }


}
