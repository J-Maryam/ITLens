package org.youcode.itlens.survey.application.dto.response;

import org.youcode.itlens.survey.application.dto.embeddable.AnswerEmbeddableDto;

import java.time.Year;
import java.util.List;

public record SurveyEditionResultResponseDTO(
        String surveyTitle,
        String description,
        Year year,
        List<ChapterResultDTO> chapters
) {
    public record ChapterResultDTO(
            String title,
            List<ChapterResultDTO> subChapters,
            List<QuestionResultDTO> questions
    ) {}

    public record QuestionResultDTO(
            String text,
            List<AnswerEmbeddableDto> answers,
            int totalAnswers
    ) {}
}