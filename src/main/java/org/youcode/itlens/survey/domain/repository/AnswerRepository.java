package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.survey.domain.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
