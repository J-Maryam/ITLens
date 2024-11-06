package org.youcode.itlens.survey.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnswerRequestDto(
        @NotBlank @Size(min = 3, max = 255)String text,

        @NotNull
        Long questionId
) {
}
