package org.youcode.itlens.dto.Question;

import org.youcode.itlens.entity.enums.QuestionType;

public record UpdateQuestionDTO(
        String text,
        Integer answerCount,
        QuestionType questionType,
        Integer subjectId
) {
}