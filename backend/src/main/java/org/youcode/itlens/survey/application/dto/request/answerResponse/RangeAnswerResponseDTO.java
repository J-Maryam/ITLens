package org.youcode.itlens.survey.application.dto.request.answerResponse;

public record RangeAnswerResponseDTO(
        Long questionId,
        String answerRange
) implements ResponseDTO {
}
