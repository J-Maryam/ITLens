package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.common.application.service.GenericService;
import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;
import org.youcode.itlens.survey.application.dto.response.SubjectResponseDto;
import org.youcode.itlens.survey.domain.entities.Subject;

import java.util.List;

public interface SubjectService extends GenericService<Subject, Long, SubjectRequestDto, SubjectResponseDto> {
    List<SubjectResponseDto> findAllBySurveyEdition(Long id);
}
