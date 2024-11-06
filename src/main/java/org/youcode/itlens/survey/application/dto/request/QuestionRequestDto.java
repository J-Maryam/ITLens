package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.youcode.itlens.survey.domain.entities.QuestionType;
import org.youcode.itlens.survey.domain.entities.Subject;

public record QuestionRequestDto(
        @NotBlank
        String text,

        @Enumerated(EnumType.STRING)
        @NotBlank
        QuestionType questionType,

        @ManyToOne
        @JoinColumn(name = "subject_id", nullable = false)
        Subject subject
) {
}
