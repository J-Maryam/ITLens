package org.youcode.itlens.dto.Subject;

public record UpdateSubjectDTO(
        String title,
        Integer surveyEditionId,
        Integer parentSubjectId
) {
}
