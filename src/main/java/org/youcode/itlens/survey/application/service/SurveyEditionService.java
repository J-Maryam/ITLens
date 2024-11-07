package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.common.application.service.GenericService;
import org.youcode.itlens.survey.application.dto.request.SurveyEditionRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

public interface SurveyEditionService extends GenericService<SurveyEdition, Long, SurveyEditionRequestDto, SurveyEditionResponseDto> {
}
