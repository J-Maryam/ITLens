package org.youcode.itlens.survey.application.dto.request.answerResponse;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleAnswerResponseDTO.class, name = "single"),
        @JsonSubTypes.Type(value = MultipleAnswersResponseDTO.class, name = "multiple"),
        @JsonSubTypes.Type(value = RangeAnswerResponseDTO.class, name = "range")
})
public sealed interface ResponseDTO permits SingleAnswerResponseDTO, MultipleAnswersResponseDTO, RangeAnswerResponseDTO {
}
