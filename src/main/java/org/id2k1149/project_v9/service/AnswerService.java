package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            return answerRepository.findAnswerById(id);
        } else {
            throw new IllegalStateException(
                    "Answer with id " + id + " does not exist");
        }
    }

    public void addAnswer(Answer newAnswer) {
        newAnswer.setActive(true);
        answerRepository.save(newAnswer);
    }
}