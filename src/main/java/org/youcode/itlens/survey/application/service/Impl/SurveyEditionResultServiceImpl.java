package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;
import org.youcode.itlens.survey.application.service.SurveyEditionResultService;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyEditionResultServiceImpl implements SurveyEditionResultService {
    @Override
    public SurveyEditionResultResponseDTO getResults(Long surveyId) {
        return null;
    }
}
