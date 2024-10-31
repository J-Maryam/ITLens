package org.youcode.itlens.dto.Survey;

public record UpdateSurveyDTO(
        String title,
        String description,
        Integer ownerId
) {
}
