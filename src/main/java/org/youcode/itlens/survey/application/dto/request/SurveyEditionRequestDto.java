package org.youcode.itlens.survey.application.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.survey.domain.entities.Survey;

import java.time.LocalDate;

public record SurveyEditionRequestDto(
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
        @Exists(entityClass = Survey.class, message = "no survey with this id")
        Long surveyId
) {
}
