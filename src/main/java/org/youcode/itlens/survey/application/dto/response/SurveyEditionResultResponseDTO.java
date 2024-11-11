package org.youcode.itlens.survey.application.dto.response;

import java.util.List;

public record SurveyEditionResultResponseDTO(
        String surveyTitle,
        String description,
        int year,
        List<ChapterResultDTO> chapters
) {

    public record ChapterResultDTO(
            String title,
            List<ChapterResultDTO> subChapters,
            List<QuestionResultDTO> questions
    ) {}

    public record QuestionResultDTO(
            String questionText,
            List<AnswerDTO> answers,
            int totalAnswers
    ) {}

    public record AnswerDTO(
            String answerText,
            int count
    ) {}
}
