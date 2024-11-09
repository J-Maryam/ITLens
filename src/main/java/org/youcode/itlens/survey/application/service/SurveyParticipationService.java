package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.survey.application.dto.request.answerResponse.SurveyParticipationRequest;

public interface SurveyParticipationService {
    void participate(String surveyId, SurveyParticipationRequest request);
}
