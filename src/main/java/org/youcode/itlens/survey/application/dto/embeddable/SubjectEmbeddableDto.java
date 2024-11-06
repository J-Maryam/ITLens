package org.youcode.itlens.survey.application.dto.embeddable;

public record SubjectEmbeddableDto(
        Long id,
        String title,
        SurveyEditionEmbeddableDto surveyEdition,
        SubjectEmbeddableDto parentSubject
) {
}
