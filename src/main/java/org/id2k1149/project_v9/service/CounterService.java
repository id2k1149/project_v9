package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.repository.CounterRepository;
import org.id2k1149.project_v9.util.exception.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CounterService {

    private final CounterRepository counterRepository;
    private final VoterService voterService;

    public CounterService(CounterRepository counterRepository,
                          VoterService voterService) {
        this.counterRepository = counterRepository;
        this.voterService = voterService;
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

    public void vote(VotesCounter votesCounter) {
        VotesCounter newVotesCounter = new VotesCounter();
        Answer newAnswer = votesCounter.getAnswer();
        int votes = 0;
        Optional<VotesCounter> optionalVotesCounter = counterRepository
                .findByVotesDateAndAnswer(LocalDate.now(), newAnswer);
        if (optionalVotesCounter.isPresent()) {
            newVotesCounter = optionalVotesCounter.get();
            votes = newVotesCounter.getVotes();
        }
        votes += 1;
        newVotesCounter.setAnswer(newAnswer);
        newVotesCounter.setVotes(votes);
        counterRepository.save(newVotesCounter);
        voterService.voterCheck(newAnswer);
    }
}