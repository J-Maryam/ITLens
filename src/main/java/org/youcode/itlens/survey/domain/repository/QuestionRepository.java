package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.itlens.survey.domain.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
