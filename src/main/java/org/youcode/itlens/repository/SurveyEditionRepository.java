package org.youcode.itlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.entity.SurveyEdition;

@Repository
public interface SurveyEditionRepository extends JpaRepository<SurveyEdition, Integer> {
}
