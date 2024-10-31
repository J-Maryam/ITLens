package org.youcode.itlens.dto.Question;

import org.youcode.itlens.dto.Subject.EmbeddedSubjectDTO;
import org.youcode.itlens.dto.Survey.EmbeddedSurveyDTO;
import org.youcode.itlens.entity.enums.QuestionType;

import java.util.List;

public record QuestionResponseDTO(
        Integer id,
        String text,
        Integer answerCount,
        QuestionType questionType,
        EmbeddedSubjectDTO subject
) {
}
