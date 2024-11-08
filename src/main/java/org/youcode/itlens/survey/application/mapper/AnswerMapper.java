package org.youcode.itlens.survey.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.itlens.common.application.mapper.GenericMapper;
import org.youcode.itlens.survey.application.dto.request.AnswerRequestDto;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.domain.entities.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper extends GenericMapper<Answer, AnswerRequestDto, AnswerResponseDto> {
}
