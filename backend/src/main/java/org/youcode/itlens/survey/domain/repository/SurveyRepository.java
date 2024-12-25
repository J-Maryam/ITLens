package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.survey.domain.entities.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    boolean existsByTitle(String title);
}
