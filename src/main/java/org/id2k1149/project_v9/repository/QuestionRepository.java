package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findQuestionById(Long id);

    Question findQuestionByQuestionTitleAndDatePublished(String questionTitle, LocalDate datePublished);

    Optional<Question> findQuestionsByDatePublished(LocalDate date);

    List<Question> findQuestionsByQuestionTitle(String questionTitle);
}
