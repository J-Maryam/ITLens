package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.youcode.itlens.common.application.validation.annotation.UniqueValue;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.survey.domain.entities.Survey;

public record SurveyRequestDto(
        @Column(nullable = false)
        @UniqueValue(entityClass = Survey.class, fieldName = "title")
        String title,

        @Column(nullable = false)
        String description,

        @ManyToOne
        @JoinColumn(name = "owner_id")
        Owner owner
) {
}
