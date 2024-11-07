package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

@Repository
public interface SurveyEditionRepository extends JpaRepository<SurveyEdition, Long> {
}
