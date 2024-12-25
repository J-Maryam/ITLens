package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.survey.application.dto.embeddable.SubjectEmbeddableDto;
import org.youcode.itlens.survey.application.dto.embeddable.SurveyEmbeddableDto;

import java.time.LocalDate;
import java.util.List;

public record SurveyEditionResponseDto(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        int year,
        SurveyEmbeddableDto survey,
        List<SubjectEmbeddableDto> subjects
) {
}
