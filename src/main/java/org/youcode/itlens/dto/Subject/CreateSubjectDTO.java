package org.youcode.itlens.dto.Subject;

public record CreateSubjectDTO(
        String title,
        Integer surveyEditionId,
        Integer parentSubjectId
) {
}
