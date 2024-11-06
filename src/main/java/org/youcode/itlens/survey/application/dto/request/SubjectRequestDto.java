package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

public record SubjectRequestDto(
        @NotBlank
        String title,

        @ManyToOne
        SurveyEdition surveyEdition,

        @ManyToOne
        Subject parentSubject
) {
}
