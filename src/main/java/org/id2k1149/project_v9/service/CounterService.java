package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.repository.CounterRepository;
import org.id2k1149.project_v9.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CounterService {

    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public List<VotesCounter> getCounters() {
        return counterRepository.findAll();
    }

    public VotesCounter getCounter(Long id) {
        if (counterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        return counterRepository.getById(id);
    }

    public void addCounter(VotesCounter newCounter) {
        counterRepository.save(newCounter);
    }

    public void updateCounter(Long id, VotesCounter counter) {
        if (counterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        VotesCounter counterToUpdate = counterRepository.findById(id).get();
        counterToUpdate.setAnswer(counter.getAnswer());
        counterToUpdate.setVotesDate(counter.getVotesDate());
        counterToUpdate.setVotes(counter.getVotes());
        counterRepository.save(counterToUpdate);
    }

    public void deleteCounter(Long id) {
        if (counterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        counterRepository.deleteById(id);
    }
}