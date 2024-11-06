package org.youcode.itlens.survey.application.dto.embeddable;

import org.youcode.itlens.owner.application.dto.OwnerEmbeddableDTO;

public record OwnerEmbeddableDto(
        Long id,
        String title,
        String description,
        OwnerEmbeddableDTO owner
) {
}
