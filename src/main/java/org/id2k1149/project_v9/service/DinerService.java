package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Diner;
import org.id2k1149.project_v9.repository.DinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DinerService {

    private final DinerRepository dinerRepository;

    @Autowired
    public DinerService(DinerRepository dinerRepository) {
        this.dinerRepository = dinerRepository;
    }

    public List<Diner> getDiners() {
        return dinerRepository.findAll();
    }

    public Diner getDiner(Long id) {
        Optional<Diner> optionalDiner = dinerRepository.findById(id);
        if (optionalDiner.isPresent()) {
            return dinerRepository.getById(id);
        } else {
            throw new IllegalStateException(
                    "Diner with id " + id + " does not exist");
        }
    }

    public void addDiner(Diner newDiner) {
        dinerRepository.save(newDiner);
    }
}