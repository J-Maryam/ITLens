package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.survey.application.dto.request.answerResponse.SurveyParticipationRequest;
import org.youcode.itlens.survey.application.service.AnswerService;
import org.youcode.itlens.survey.application.service.QuestionService;
import org.youcode.itlens.survey.application.service.SurveyParticipationService;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @Override
    public void participate(String surveyId, SurveyParticipationRequest request) {

    }
}
