package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.common.application.service.GenericService;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.domain.entities.Survey;

public interface SurveyService extends GenericService<Survey, Long, SurveyRequestDto, SurveyResponseDto> {
}
