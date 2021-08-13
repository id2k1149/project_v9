package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.to.AnswerTo;
import org.id2k1149.project_v9.util.AnswerUtil;
import org.id2k1149.project_v9.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InfoService {
    private final InfoRepository infoRepository;
    private final AnswerRepository answerRepository;

    public InfoService(InfoRepository infoRepository,
                       AnswerRepository answerRepository) {
        this.infoRepository = infoRepository;
        this.answerRepository = answerRepository;
    }

    public List<Info> getAllInfo() {
        return infoRepository.findAll();
    }

    public Info getInfo(Long id) {
        if (infoRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        return infoRepository.getById(id);
    }

    public void addInfo(Info newInfo, Long answerId) {
        if (answerRepository.findById(answerId).isEmpty()) {
            throw new NotFoundException(answerId + " does not exist");
        }
        newInfo.setAnswer(answerRepository.getById(answerId));
        infoRepository.save(newInfo);
    }

    public void updateInfo(Info info, Long id) {
        if (infoRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        Info infoToUpdate = infoRepository.findById(id).get();
        infoToUpdate.setAnswer(info.getAnswer());
        infoToUpdate.setDate(info.getDate());
        infoToUpdate.setDetails(info.getDetails());
        infoRepository.save(infoToUpdate);
    }

    public void deleteInfo(Long id) {
        if (infoRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        infoRepository.deleteById(id);
    }

    public List<Info> getByDate(LocalDate date) {
        return infoRepository.getByDate(date);
    }

    public List<Answer> getTodayAnswersInfo() {
        return infoRepository
                .getByDate(LocalDate.now())
                .stream()
                .map(Info::getAnswer)
                .collect(Collectors.toList());
    }

    public List<AnswerTo> checkTime() {
        List<AnswerTo> answerToList = new ArrayList<>();
        if (LocalTime.now().getHour() < 24) answerToList = AnswerUtil.getAnswersTo(getTodayAnswersInfo(),
                getByDate(LocalDate.now()));
        return answerToList;
    }
}