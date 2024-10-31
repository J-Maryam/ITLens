package org.youcode.itlens.dto.Owner;

import org.youcode.itlens.dto.Survey.EmbeddedSurveyDTO;
import org.youcode.itlens.entity.Survey;

import java.util.List;

public record OwnerResponseDTO(
        Integer id,
        String name,
        List<EmbeddedSurveyDTO> surveys
) {
}
