package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;

import java.time.LocalDate;
import java.util.List;

public record SurveyEditionResponseDto(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        int year,
        SurveyResponseDto survey,
        List<SubjectRequestDto> subjects
) {
}
