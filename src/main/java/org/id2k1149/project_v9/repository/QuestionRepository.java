package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question, Long> {
//    List<Question> findQuestionByQuestionTitleAndDatePublished(String questionTitle, Date datePublished);
//    List<Question> findQuestionByQuestionId(Long questionId);

}
