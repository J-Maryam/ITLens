package org.youcode.itlens.dto.Survey;

import org.youcode.itlens.dto.Owner.EmbeddedOwnerDTO;
import org.youcode.itlens.dto.SurveyEdition.EmbeddedSurveyEditionDTO;

import java.util.List;

public record SurveyResponseDTO(
        Integer id,
        String title,
        String description,
        EmbeddedOwnerDTO owner,
        List<EmbeddedSurveyEditionDTO> surveyEditions
) {
}
