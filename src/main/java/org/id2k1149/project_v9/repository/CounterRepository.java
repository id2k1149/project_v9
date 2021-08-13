package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.VotesCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CounterRepository extends JpaRepository<VotesCounter, Long> {
    Optional<VotesCounter> findByDateAndAnswer(LocalDate localDate, Answer answer);
    List<VotesCounter> findByDate(LocalDate localDate);
}