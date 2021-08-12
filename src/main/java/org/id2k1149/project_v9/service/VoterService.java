package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.CounterRepository;
import org.id2k1149.project_v9.repository.UserRepository;
import org.id2k1149.project_v9.repository.VoterRepository;
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
public class VoterService {

    private final VoterRepository voterRepository;
    private final UserRepository userRepository;
    private final CounterRepository counterRepository;

    public VoterService(VoterRepository voterRepository,
                        UserRepository userRepository,
                        CounterRepository counterRepository) {
        this.voterRepository = voterRepository;
        this.userRepository = userRepository;
        this.counterRepository = counterRepository;
    }

    public List<Voter> getVoters() {
        return voterRepository.findAll();
    }

    public Voter getVoter(Long id) {
        if (voterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        return voterRepository.getById(id);
    }

    public void addVoter(Voter newVoter) {
        voterRepository.save(newVoter);
    }

    public void updateVoter(Long id, Voter voter) {
        if (voterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        Voter voterToUpdate = voterRepository.findById(id).get();
        voterToUpdate.setAnswer(voter.getAnswer());
        voterToUpdate.setVotesDate(voter.getVotesDate());
        voterToUpdate.setUser(voter.getUser());
        voterRepository.save(voterToUpdate);
    }

    public void deleteVoter(Long id) {
        if (voterRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        voterRepository.deleteById(id);
    }

    public void checkVoter(Answer newAnswer) {
            Optional<Voter> optionalVoter = checkUser();
            Voter voter = new Voter();
            if (optionalVoter.isPresent()) {
                voter = optionalVoter.get();
                Answer voterAnswer = voter.getAnswer();
                VotesCounter oldVotesCounter = counterRepository
                        .findByVotesDateAndAnswer(LocalDate.now(), voterAnswer)
                        .get();
                int votes = oldVotesCounter.getVotes() - 1;
                oldVotesCounter.setVotes(votes);
                counterRepository.save(oldVotesCounter);

            voter.setAnswer(newAnswer);
            voterRepository.save(voter);
        }
    }

    public Optional<Voter> checkUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();

        User user = userRepository.findUserByUsername(username);

        Voter voter = new Voter();
        voter.setUser(user);

        return voterRepository.findByUserAndVotesDate(user, LocalDate.now());
    }
}