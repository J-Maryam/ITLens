package org.youcode.itlens.dto.Survey;

import org.youcode.itlens.dto.Owner.EmbeddedOwnerDTO;

public record EmbeddedSurveyDTO(
        Integer id,
        String title,
        String description,
        EmbeddedOwnerDTO owner
) {
}
