package org.youcode.itlens.survey.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.common.application.validation.annotation.UniqueValue;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.survey.domain.entities.Survey;

public record SurveyRequestDto(
        @NotBlank
        @UniqueValue(entityClass = Survey.class, fieldName = "title", message = "survey title already exists")
        String title,

        @NotBlank
        String description,

        @NotNull
        @Exists(entityClass = Owner.class, message = "owner id does not exists")
        Long ownerId
) {
}
