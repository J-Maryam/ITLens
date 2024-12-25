package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.survey.application.dto.embeddable.AnswerEmbeddableDto;
import org.youcode.itlens.survey.application.dto.embeddable.SubjectEmbeddableDto;
import org.youcode.itlens.survey.domain.entities.enums.QuestionType;

import java.util.List;

public record QuestionResponseDto(
        Long id,
        String text,
        Integer answerCount,
        QuestionType questionType,
        SubjectEmbeddableDto subject,
        List<AnswerEmbeddableDto> answers
) {
}
