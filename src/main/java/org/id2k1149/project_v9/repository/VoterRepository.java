package org.id2k1149.project_v9.repository;


import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByUser(User user);
}
