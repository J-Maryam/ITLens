package org.youcode.itlens.survey.application.mapper;

import org.youcode.itlens.common.application.mapper.GenericMapper;
import org.youcode.itlens.survey.application.dto.request.QuestionRequestDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.domain.entities.Question;

public interface QuestionMapper extends GenericMapper<Question, QuestionRequestDto, QuestionResponseDto> {
}
