package org.youcode.itlens.survey.application.dto.request.answerResponse;

import org.youcode.itlens.survey.domain.entities.Answer;

import java.util.List;

public record ResponseDTO(
        Long questionId,
        List<Long> answersId
) {
}
