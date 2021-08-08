package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {

    private final QuestionRepository questionRepository;
    private final InfoRepository infoRepository;

    @Autowired
    public AdminService(QuestionRepository questionRepository, InfoRepository infoRepository) {
        this.questionRepository = questionRepository;
        this.infoRepository = infoRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public List<Answer> getAnswers() {
        List<Info> infoList = infoRepository.findByDateOfInfo(LocalDate.now());
        List<Answer> answersList = new ArrayList<>();
        for (Info info : infoList) {
            answersList.add(info.getAnswer());
        }
        return answersList;
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }
}
