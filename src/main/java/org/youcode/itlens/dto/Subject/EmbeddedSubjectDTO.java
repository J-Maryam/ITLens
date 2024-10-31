package org.youcode.itlens.dto.Subject;

import org.youcode.itlens.dto.Owner.EmbeddedOwnerDTO;
import org.youcode.itlens.dto.Question.EmbeddedQuestionDTO;
import org.youcode.itlens.dto.SurveyEdition.EmbeddedSurveyEditionDTO;

import java.util.List;

public record EmbeddedSubjectDTO(
        Integer id,
        String title,
        EmbeddedSurveyEditionDTO surveyEdition,
        EmbeddedSurveyEditionDTO parentSubject
) {
}