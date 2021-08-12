package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.to.AnswerTo;
import org.id2k1149.project_v9.to.InfoTo;
import org.id2k1149.project_v9.util.InfoUtil;
import org.id2k1149.project_v9.util.exception.BadRequestException;
import org.id2k1149.project_v9.util.exception.NotFoundException;
import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final InfoRepository infoRepository;

    public AnswerService(AnswerRepository answerRepository,
                         InfoRepository infoRepository) {
        this.answerRepository = answerRepository;
        this.infoRepository = infoRepository;
    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswer(Long id) {
        if (answerRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        return answerRepository.getById(id);
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

    public AnswerTo getAllInfoForAnswer(Long id) {
        Answer answer = getAnswer(id);
        List<Info> infoList = infoRepository.findAll();
        List<InfoTo> infoToList = InfoUtil.getInfoTo(answer, infoList);
        return new AnswerTo(id, answer.getTitle(), infoToList);
    }
}