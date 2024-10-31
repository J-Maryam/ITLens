package org.youcode.itlens.dto.SurveyEdition;

import java.time.LocalDate;

public record UpdateSurveyDTO(
        LocalDate creationDate,
        LocalDate startDate,
        int year,
        Integer surveyId
) {
}
