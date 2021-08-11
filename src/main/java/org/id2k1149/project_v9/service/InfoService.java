package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        infoToUpdate.setDateOfInfo(info.getDateOfInfo());
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
        return infoRepository.getByDateOfInfo(date);
    }

    public List<Answer> getTodayAnswers() {
        return infoRepository
                .getByDateOfInfo(LocalDate.now())
                .stream()
                .map(Info::getAnswer)
                .collect(Collectors.toList());
    }
}