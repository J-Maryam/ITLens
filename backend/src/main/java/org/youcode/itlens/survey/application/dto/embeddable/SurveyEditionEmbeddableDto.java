package org.youcode.itlens.survey.application.dto.embeddable;

import java.time.LocalDate;

public record SurveyEditionEmbeddableDto(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        int year
//        SurveyEmbeddableDto survey
) {
}
