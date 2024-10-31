package org.youcode.itlens.dto.SurveyEdition;

import org.youcode.itlens.dto.Survey.EmbeddedSurveyDTO;

import java.time.LocalDate;

public record EmbeddedSurveyEditionDTO(
        Integer id,
        LocalDate creationDate,
        LocalDate startDate,
        int year,
        EmbeddedSurveyDTO survey
) {
}
