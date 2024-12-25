package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.survey.application.dto.embeddable.QuestionEmbeddableDto;

public record AnswerResponseDto(
        Long id,
        String text,
        Integer selectionCount,
        QuestionEmbeddableDto question
) {
}
