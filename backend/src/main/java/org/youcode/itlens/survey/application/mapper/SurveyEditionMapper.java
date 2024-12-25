package org.youcode.itlens.survey.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.itlens.common.application.mapper.GenericMapper;
import org.youcode.itlens.survey.application.dto.request.SurveyEditionRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper extends GenericMapper<SurveyEdition, SurveyEditionRequestDto, SurveyEditionResponseDto> {
    SurveyEdition toEntity(SurveyEditionRequestDto dto);
    @Mapping(target = "subjects", source = "subjects")
    SurveyEditionResponseDto toDto(SurveyEdition entity);
}
