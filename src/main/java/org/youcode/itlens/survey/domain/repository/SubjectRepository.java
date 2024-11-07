package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByTitleAndSurveyEdition(String title, SurveyEdition surveyEdition);

}
