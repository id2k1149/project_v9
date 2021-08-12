package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.VoterRepository;
import org.id2k1149.project_v9.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoterService {

    private final VoterRepository voterRepository;
    private final AnswerRepository answerRepository;

    public VoterService(VoterRepository voterRepository, AnswerRepository answerRepository) {
        this.voterRepository = voterRepository;
        this.answerRepository = answerRepository;
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

}