package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminWebController {

    private final AdminService adminService;

    @Autowired
    public AdminWebController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("admin/questions")
    public String questions(Model model) {
        List<Question> questionsList = adminService.getQuestions();
        model.addAttribute("questionsList", questionsList);
        return "admin/questions";
    }


    @GetMapping("/new")
    public String newQuestionForm(Model model) {
        List<Answer> answersList = adminService.getAnswers();
        model.addAttribute("question", new Question());
        model.addAttribute("answersList", answersList);
        return "admin/questionForm";
    }

    @PostMapping("/save")
    public String saveQuestion(Question question) {
        adminService.addQuestion(question);
        return "redirect:/admin/questions";
    }
}
