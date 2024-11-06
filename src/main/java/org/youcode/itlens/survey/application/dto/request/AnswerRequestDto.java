package org.youcode.itlens.survey.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record AnswerRequestDto(
        String text,

        @NotNull Long questionId
) {
}
