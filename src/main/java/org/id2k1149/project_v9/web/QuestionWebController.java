package org.id2k1149.project_v9.web;

import org.id2k1149.project_v9.model.*;
import org.id2k1149.project_v9.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Controller
public class QuestionWebController {

    private final QuestionRepository questionRepository;

    private final VotesCounterRepository votesCounterRepository;

    private final VoterRepository voterRepository;

    private final UserRepository userRepository;

    @Autowired
    public QuestionWebController(QuestionRepository questionRepository,
                                 VotesCounterRepository votesCounterRepository,
                                 VoterRepository voterRepository,
                                 UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.votesCounterRepository = votesCounterRepository;
        this.voterRepository = voterRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/questions")
    public String questionsForYou(Model model) {
        List<Question> questionsList = new ArrayList<>();

        if (LocalDateTime.now().getHour() < 23) {
            questionsList = questionRepository.findByDatePublished(LocalDate.now());
        }
        model.addAttribute("questionsList", questionsList);
        return "questions";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        String error_message;
        Question question = questionRepository.findById((long) id).get();
        model.addAttribute("question", question);
        Set<Answer> answersList = question.getAnswers();
        model.addAttribute("answersList", answersList);
        return "question";
    }

    /*
    @PostMapping("/vote/{id}")
    public String vote(@PathVariable("id") int id, VotesCounter votesCounter) {
        VotesCounter newVote = new VotesCounter();
        Answer answer = votesCounter.getAnswer();
        Question question = questionRepository.findById((long) id).get();
        int votes = 0;

        Optional<VotesCounter> optionalVotesCounter = votesCounterRepository.findByQuestionAndAnswer(question, answer);
        if (optionalVotesCounter.isPresent()) {
            newVote = optionalVotesCounter.get();
            votes = newVote.getVotes();
        }
        votes += 1;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
            Voter voterToEdit = optionalVoter.get();
            Answer answerToChange = answer;
            VotesCounter votesCounterToChange = optionalVotesCounter.get();
            int oldVotes = votesCounterToChange.getVotes();

            oldVotes -= 1;
            votesCounterToChange.setVotes(oldVotes);
            votesCounterRepository.save(votesCounterToChange);

        }
        voter.setAnswer(answer);
        voterRepository.save(voter);

        newVote.setQuestion(question);
        newVote.setAnswer(answer);
        newVote.setVotes(votes);
        votesCounterRepository.save(newVote);
        return "redirect:/result/{id}";
    }

     */

    @PostMapping("/vote/{id}")
    public String vote2(@PathVariable("id") int id, VotesCounter votesCounter) {
        VotesCounter newVotesCounter = new VotesCounter();
        Question question = questionRepository.getById((long) id);
        Answer newAnswer = votesCounter.getAnswer();
        int votes = 0;

        Optional<VotesCounter> optionalVotesCounter = votesCounterRepository
                .findByQuestionAndAnswer(question, newAnswer);
        if (optionalVotesCounter.isPresent()) {
            newVotesCounter = optionalVotesCounter.get();
            votes = newVotesCounter.getVotes();
        }
        votes += 1;
        newVotesCounter.setQuestion(question);
        newVotesCounter.setAnswer(newAnswer);
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
            Answer voterAnswer = voter.getAnswer();
            VotesCounter oldVotesCounter = votesCounterRepository
                    .findByQuestionAndAnswer(question, voterAnswer).get();
            votes = oldVotesCounter.getVotes() - 1;
            oldVotesCounter.setVotes(votes);
            votesCounterRepository.save(oldVotesCounter);
        }
        voter.setAnswer(newAnswer);
        voterRepository.save(voter);

        return "redirect:/result/{id}";
    }


    @GetMapping("/result/{id}")
    public String result(@PathVariable("id") int id, Model model) {
        Question question = questionRepository.findById((long) id).get();
        List<VotesCounter> votesCounterList = new ArrayList<>();
        votesCounterList = votesCounterRepository.findByQuestion(question);

        List<VotesCounter> sortedList = votesCounterList.stream()
                .sorted(Comparator.comparingInt(VotesCounter::getVotes).reversed())
                .collect(Collectors.toList());

        VotesCounter bestResult = sortedList
                .stream()
                .max(Comparator.comparing(VotesCounter::getVotes))
                .orElseThrow(NoSuchElementException::new);

        int maxVotes = bestResult.getVotes();
        String result = bestResult.getAnswer().toString();
        question.setResult(result);
        questionRepository.save(question);

        model.addAttribute("sortedList", sortedList);
        model.addAttribute("question", question);
        model.addAttribute("maxVotes", maxVotes);


        return "result";

    }


}
