package org.youcode.itlens.dto.SurveyEdition;

import org.youcode.itlens.dto.Subject.EmbeddedSubjectDTO;
import org.youcode.itlens.dto.Survey.EmbeddedSurveyDTO;

import java.time.LocalDate;
import java.util.List;

public record SurveyEditionResponseDTO(
        Integer id,
        LocalDate creationDate,
        LocalDate startDate,
        int year,
        EmbeddedSurveyDTO survey,
        List<EmbeddedSubjectDTO> subjects
) {
}
