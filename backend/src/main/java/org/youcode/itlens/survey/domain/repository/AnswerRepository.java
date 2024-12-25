package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.survey.domain.entities.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM Answer a WHERE a.id BETWEEN :startId AND :endId")
    List<Answer> findAllByIdInRange(@Param("startId") Long startId, @Param("endId") Long endId);
}
