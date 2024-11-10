package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;

public interface SurveyEditionResultService {
    SurveyEditionResultResponseDTO getResults(Long surveyId);
}
