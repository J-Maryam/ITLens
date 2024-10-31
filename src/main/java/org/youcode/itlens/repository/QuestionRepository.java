package org.youcode.itlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
