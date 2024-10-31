package org.youcode.itlens.dto.Question;

import org.youcode.itlens.dto.Subject.EmbeddedSubjectDTO;
import org.youcode.itlens.entity.enums.QuestionType;

public record EmbeddedQuestionDTO(
        Integer id,
        String text,
        Integer answerCount,
        QuestionType questionType,
        EmbeddedSubjectDTO subject
) {
}