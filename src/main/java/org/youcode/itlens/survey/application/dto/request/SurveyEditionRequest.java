package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.survey.domain.entities.Survey;

import java.time.LocalDate;

public record SurveyEditionRequest(
        @FutureOrPresent
        @NotNull
        LocalDate creationDate,

        @Future
        @NotNull
        LocalDate startDate,

        @Future
        @NotNull
        int year,

        @NotNull
        @Exists(entityClass = Survey.class, message = "no parent subject with this id")
        Long surveyId
) {
}
