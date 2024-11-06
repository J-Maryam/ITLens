package org.youcode.itlens.survey.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.itlens.common.application.mapper.GenericMapper;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.domain.entities.Survey;

@Mapper(componentModel = "spring")
public interface SurveyMapper extends GenericMapper<Survey, SurveyRequestDto, SurveyResponseDto> {
}
