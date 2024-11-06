package org.youcode.itlens.survey.application.dto.embeddable;

public record AnswerEmbeddableDto(
        Long id,
        String text,
        Integer selectionCount,
        QuestionEmbeddableDto question
) {
}
