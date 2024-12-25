package org.youcode.itlens.survey.application.dto.embeddable;

import org.youcode.itlens.survey.domain.entities.enums.QuestionType;

public record QuestionEmbeddableDto(
        Long id,
        String text,
        Integer answerCount,
        QuestionType questionType,
        SubjectEmbeddableDto subject
) {
}
