package org.youcode.itlens.survey.application.dto.request.answerResponse;

public sealed interface ResponseDTO permits SingleAnswerResponseDTO, MultipleAnswersResponseDTO, RangeAnswerResponseDTO {
}
