package org.youcode.itlens.dto.Answer;

import org.youcode.itlens.dto.Question.EmbeddedQuestionDTO;
import org.youcode.itlens.dto.Survey.EmbeddedSurveyDTO;

import java.util.List;

public record AnswerResponseDTO(
        Integer id,
        String text,
        Integer selectionCount,
        EmbeddedQuestionDTO question
) {
}
