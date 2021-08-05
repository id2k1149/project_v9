package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            return questionRepository.findQuestionByQuestionId(questionId);
        } else {
            throw new IllegalStateException(
                    "Question with id " + questionId + " does not exist");
        }
    }

    public void addQuestion(Question newQuestion) {
        LocalDate today = LocalDate.now();

        Optional<Question> optionalQuestion = questionRepository
                .findQuestionsByDatePublished(today);

        if (optionalQuestion.isPresent()) {
            throw new BadRequestException("The question " + newQuestion.getQuestionTitle() + " is already published");

        }

        newQuestion.setDatePublished(today);


    }
}