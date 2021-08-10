package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InfoService {

    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
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

    public void addInfo(Info newInfo) {
        infoRepository.save(newInfo);
    }

    public void updateInfo(Info info,
                             Long id) {
        Info infoToUpdate;

        if (infoRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        } else {
            infoToUpdate = infoRepository.findById(id).get();
        }

        infoRepository.save(info);
    }

    public void deleteInfo(Long id) {
        if (infoRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        infoRepository.deleteById(id);
    }
}