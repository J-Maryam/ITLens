package org.youcode.itlens.dto.Answer;

import org.youcode.itlens.dto.Question.EmbeddedQuestionDTO;

public record EmbeddedAnswerDTO(
        Integer id,
        String text,
        Integer selectionCount,
        EmbeddedQuestionDTO question
) {
}
