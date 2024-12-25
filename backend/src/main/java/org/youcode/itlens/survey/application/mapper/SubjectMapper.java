package org.youcode.itlens.survey.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.itlens.common.application.mapper.GenericMapper;
import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;
import org.youcode.itlens.survey.application.dto.response.SubjectResponseDto;
import org.youcode.itlens.survey.domain.entities.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends GenericMapper<Subject, SubjectRequestDto, SubjectResponseDto> {
}
