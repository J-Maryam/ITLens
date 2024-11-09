package org.youcode.itlens.survey.application.dto.request.answerResponse;

import java.util.List;

public record MultipleAnswersResponseDTO(
        Long questionId,
        List<Long> answersId
) {
}
