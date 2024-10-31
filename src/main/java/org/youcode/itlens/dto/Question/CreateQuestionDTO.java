package org.youcode.itlens.dto.Question;

import org.youcode.itlens.entity.enums.QuestionType;

public record CreateQuestionDTO(
        String text,
        Integer answerCount,
        QuestionType questionType,
        Integer subjectId
) {
}
