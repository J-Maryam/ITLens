package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;
import org.youcode.itlens.survey.application.mapper.SurveyEditionResultMapper;
import org.youcode.itlens.survey.application.service.SurveyEditionResultService;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.youcode.itlens.survey.domain.repository.SurveyEditionRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyEditionResultServiceImpl implements SurveyEditionResultService {
    private final SurveyEditionRepository surveyEditionRepository;
    private final SurveyEditionResultMapper surveyEditionResultMapper;

    @Override
    public SurveyEditionResultResponseDTO getResults(Long surveyId) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition not found with id: " + surveyId));

        return surveyEditionResultMapper.mapSurveyToSurveyEditionResultResponse(surveyEdition);
    }
}
