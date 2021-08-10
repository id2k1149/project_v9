package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.*;
import org.id2k1149.project_v9.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MultiService {


    private final VotesCounterRepository votesCounterRepository;
    private final VoterRepository voterRepository;
    private final UserRepository userRepository;

    @Autowired
    public MultiService(
                        VotesCounterRepository votesCounterRepository,
                        VoterRepository voterRepository,
                        UserRepository userRepository) {

        this.votesCounterRepository = votesCounterRepository;
        this.voterRepository = voterRepository;
        this.userRepository = userRepository;
    }

    /*
    public void vote(int id, VotesCounter votesCounter) {
        VotesCounter newVotesCounter = new VotesCounter();
        Question question = questionRepository.getById((long) id);
        Diner newDiner = votesCounter.getAnswer();
        int votes = 0;

        Optional<VotesCounter> optionalVotesCounter = votesCounterRepository
                .findByQuestionAndAnswer(question, newDiner);
        if (optionalVotesCounter.isPresent()) {
            newVotesCounter = optionalVotesCounter.get();
            votes = newVotesCounter.getVotes();
        }
        votes += 1;
        newVotesCounter.setQuestion(question);
        newVotesCounter.setAnswer(newDiner);
        newVotesCounter.setVotes(votes);
        votesCounterRepository.save(newVotesCounter);

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findUserByUsername(username);

        Voter voter = new Voter();
        voter.setUser(user);
        voter.setQuestion(question);

        Optional<Voter> optionalVoter = voterRepository.findByUserAndQuestion(user, question);
        if (optionalVoter.isPresent()) {
            voter = optionalVoter.get();
            Diner voterDiner = voter.getAnswer();
            VotesCounter oldVotesCounter = votesCounterRepository
                    .findByQuestionAndAnswer(question, voterDiner).get();
            votes = oldVotesCounter.getVotes() - 1;
            oldVotesCounter.setVotes(votes);
            votesCounterRepository.save(oldVotesCounter);
        }
        voter.setAnswer(newDiner);
        voterRepository.save(voter);
    }

     */

}
