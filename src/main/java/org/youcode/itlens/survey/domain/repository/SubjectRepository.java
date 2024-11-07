package org.youcode.itlens.survey.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.itlens.survey.domain.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
