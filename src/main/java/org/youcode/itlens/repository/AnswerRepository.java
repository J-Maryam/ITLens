package org.youcode.itlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
