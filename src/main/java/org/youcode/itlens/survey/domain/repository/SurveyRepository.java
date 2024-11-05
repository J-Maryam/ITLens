package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.itlens.survey.domain.entities.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
