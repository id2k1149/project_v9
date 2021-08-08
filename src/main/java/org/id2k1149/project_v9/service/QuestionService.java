package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Question getQuestion(Long id) {
        if (questionRepository.findById(id).isPresent()) {
            return questionRepository.findById(id).get();
        } else {
            throw new IllegalStateException(
                    "Question with id " + id + " does not exist");
        }
    }

    public void addQuestion(Question newQuestion) {
        LocalDate today = LocalDate.now();

//        Optional<Question> optionalQuestion = questionRepository
//                .findQuestionsByDatePublished(today);
//
//        if (optionalQuestion.isPresent()) {
//            throw new BadRequestException("The question " + newQuestion.getQuestionTitle() + " is already published");
//
//        }

        if (newQuestion.getQuestionTitle() == null) {
            newQuestion.setQuestionTitle("Where to have a lunch?");
        }

        newQuestion.setDatePublished(today);

        questionRepository.save(newQuestion);
    }

    public void deleteQuestion(Long id) {
        if(!questionRepository.existsById(id)) {
            throw new NotFoundException(
                    "Question with id " + id + " does not exists");
        }
        questionRepository.deleteById(id);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> getQuestionsForUser() {
        List<Question> questionsList = new ArrayList<>();

        if (LocalDateTime.now().getHour() < 23) {
            questionsList = questionRepository.findByDatePublished(LocalDate.now());
        }
        return questionsList;
    }


}