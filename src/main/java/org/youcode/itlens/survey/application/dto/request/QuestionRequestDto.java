package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.survey.domain.entities.enums.QuestionType;
import org.youcode.itlens.survey.domain.entities.Subject;

public record QuestionRequestDto(
        @NotBlank
        String text,

        @Enumerated(EnumType.STRING)
        @NotBlank
        QuestionType questionType,

        @NotNull
        @Exists(entityClass = Subject.class)
        Long subjectId
) {
}
