package org.youcode.itlens.survey.application.service.Impl;

import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.service.SurveyService;

import java.util.List;

public class SurveyServiceImpl implements SurveyService {
    @Override
    public List<SurveyResponseDto> getAll() {
        return List.of();
    }

    @Override
    public SurveyResponseDto getById(Long aLong) {
        return null;
    }

    @Override
    public SurveyResponseDto create(SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public SurveyResponseDto update(Long aLong, SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
