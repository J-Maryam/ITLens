package org.youcode.itlens.survey.application.dto.request.answerResponse;

import java.util.List;

public record SurveyParticipationRequest(
        List<ResponseDTO> responses
) {
}
