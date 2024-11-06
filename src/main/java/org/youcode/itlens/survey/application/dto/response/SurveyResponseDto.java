package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.owner.application.dto.OwnerEmbeddableDTO;
import org.youcode.itlens.survey.application.dto.embeddable.SurveyEditionEmbeddableDto;

import java.util.List;

public record SurveyResponseDto(
        Long id,
        String title,
        String description,
        OwnerEmbeddableDTO owner,
        List<SurveyEditionEmbeddableDto> surveyEditions
) {
}
