package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByTitleAndSurveyEdition(String title, SurveyEdition surveyEdition);
    Page<Subject> findAllBySurveyEditionId(Long surveyEditionId, Pageable pageable);
}
