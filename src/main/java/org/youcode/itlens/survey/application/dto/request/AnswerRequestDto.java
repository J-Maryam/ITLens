package org.youcode.itlens.survey.application.dto.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.survey.domain.entities.Question;

public record AnswerRequestDto(
        String text,

        @Exists(entityClass = Question.class, message = "Question id does not exists")
        @NotNull Long questionId
) {
}
