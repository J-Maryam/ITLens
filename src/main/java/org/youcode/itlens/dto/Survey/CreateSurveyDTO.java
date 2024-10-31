package org.youcode.itlens.dto.Survey;

public record CreateSurveyDTO(
        String title,
        String description,
        Integer ownerId
) {
}
