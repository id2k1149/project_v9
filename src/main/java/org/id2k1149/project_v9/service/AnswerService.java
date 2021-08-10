package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Role;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        if (answerRepository.findById(id).isPresent()) {
            return answerRepository.getById(id);
        } else {
            throw new NotFoundException(id + " does not exist");
        }
    }

    public void addAnswer(Answer newAnswer) {
        Optional<Answer> optionalAnswer = Optional.ofNullable(answerRepository.findAnswerByTitle(newAnswer.getTitle()));
        if (optionalAnswer.isPresent()) {
            throw new BadRequestException("The name " + newAnswer.getTitle() + " is already used");
        }
        answerRepository.save(newAnswer);
    }

    public void updateAnswer(Answer answer,
                             Long id) {
        Answer answerToUpdate;
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        } else {
            answerToUpdate = optionalAnswer.get();
        }

        String newTitle = answer.getTitle();
        if (newTitle != null) {
            answerToUpdate.setTitle(newTitle);
        }

        answerRepository.save(answerToUpdate);
    }

    public void deleteAnswer(Long id) {
        if (answerRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        answerRepository.deleteById(id);
    }
}