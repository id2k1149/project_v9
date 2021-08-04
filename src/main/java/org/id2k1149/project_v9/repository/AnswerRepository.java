package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AnswerRepository extends JpaRepository<Answer, Long> {
//    List<Answer> findAnswerByAnswerTitle(String answerTitle);
}
