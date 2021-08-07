package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.VotesCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface VotesCounterRepository extends JpaRepository<VotesCounter, Long> {
}
