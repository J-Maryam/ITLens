package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.survey.application.dto.embeddable.QuestionEmbeddableDto;
import org.youcode.itlens.survey.application.dto.embeddable.SubjectEmbeddableDto;
import org.youcode.itlens.survey.application.dto.embeddable.SurveyEditionEmbeddableDto;

import java.util.List;

public record SubjectResponseDto(
        Long id,
        String title,
        SurveyEditionEmbeddableDto surveyEdition,
        SubjectEmbeddableDto parentSubject,
        List<SubjectEmbeddableDto> subSubjects,
        List<QuestionEmbeddableDto> questions
) {
}
