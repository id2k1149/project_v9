package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Counter;
import org.id2k1149.project_v9.repository.CounterRepository;
import org.id2k1149.project_v9.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CounterService {

    private final CounterRepository counterRepository;
    private final VoterService voterService;
    private final InfoService infoService;

    public CounterService(CounterRepository counterRepository,
                          VoterService voterService,
                          InfoService infoService) {
        this.counterRepository = counterRepository;
        this.voterService = voterService;
        this.infoService = infoService;
    }

    public List<Counter> getCounters() {
        return counterRepository.findAll();
    }

    public Counter getCounter(Long id) {
        if (counterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        return counterRepository.getById(id);
    }

    public void addCounter(Counter newCounter) {
        counterRepository.save(newCounter);
    }

    public void updateCounter(Long id, Counter counter) {
        if (counterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        Counter counterToUpdate = counterRepository.findById(id).get();
        counterToUpdate.setAnswer(counter.getAnswer());
        counterToUpdate.setDate(counter.getDate());
        counterToUpdate.setVotes(counter.getVotes());
        counterRepository.save(counterToUpdate);
    }

    public void deleteCounter(Long id) {
        if (counterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        counterRepository.deleteById(id);
    }

    public void vote(Counter counter) {
        Counter newCounter = new Counter();
        Answer newAnswer = counter.getAnswer();
        int votes = 0;
        Optional<Counter> optionalVotesCounter = counterRepository
                .findByDateAndAnswer(LocalDate.now(), newAnswer);
        if (optionalVotesCounter.isPresent()) {
            newCounter = optionalVotesCounter.get();
            votes = newCounter.getVotes();
        }
        votes += 1;
        newCounter.setAnswer(newAnswer);
        newCounter.setVotes(votes);
        counterRepository.save(newCounter);
        voterService.checkVoter(newAnswer);
    }

    public List<Counter> getResult() {
        List<Counter> counterList = counterRepository.findByDate(LocalDate.now());
        if (counterList.size() == 0) return counterList;

        List<Counter> sortedList = counterList.stream()
                .sorted(Comparator.comparingInt(Counter::getVotes).reversed())
                .collect(Collectors.toList());

        Counter bestResult = sortedList
                .stream()
                .max(Comparator.comparing(Counter::getVotes))
                .orElseThrow(NoSuchElementException::new);

        return sortedList;
    }
}