package org.youcode.itlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
}
