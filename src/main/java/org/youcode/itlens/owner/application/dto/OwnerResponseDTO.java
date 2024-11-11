package org.youcode.itlens.owner.application.dto;

import org.youcode.itlens.survey.application.dto.embeddable.SurveyEmbeddableDto;
import org.youcode.itlens.survey.domain.entities.Survey;

import java.util.List;

public record OwnerResponseDTO(
        Long id,
        String name,
        List<SurveyEmbeddableDto>surveys
) {
}
