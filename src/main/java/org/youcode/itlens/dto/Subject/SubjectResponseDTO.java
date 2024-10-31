package org.youcode.itlens.dto.Subject;


import org.youcode.itlens.dto.Question.EmbeddedQuestionDTO;
import org.youcode.itlens.dto.SurveyEdition.EmbeddedSurveyEditionDTO;

import java.util.List;

public record SubjectResponseDTO(
        Integer id,
        String title,
        EmbeddedSurveyEditionDTO surveyEdition,
        EmbeddedSurveyEditionDTO parentSubject,
        List<EmbeddedQuestionDTO> questions
) {
}
