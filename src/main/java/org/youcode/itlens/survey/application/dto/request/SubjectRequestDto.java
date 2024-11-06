package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

public record SubjectRequestDto(
        @NotBlank String title,

        @NotNull
        @Exists(entityClass = SurveyEdition.class, message = "no survey edition with this id")
        Long surveyEditionId,

        @NotNull
        @Exists(entityClass = Subject.class, message = "no parent subject with this id")
        Long parentSubjectId
) {
}
