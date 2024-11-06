package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.mapper.SurveyMapper;
import org.youcode.itlens.survey.application.service.SurveyService;
import org.youcode.itlens.survey.domain.entities.Survey;
import org.youcode.itlens.survey.domain.repository.SurveyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;
    private final SurveyMapper mapper;

    @Override
    public List<SurveyResponseDto> getAll() {
        List<Survey> surveys = repository.findAll();
        return surveys.stream().map(mapper::toDto).toList();
    }

    @Override
    public SurveyResponseDto getById(Long id) {
        Survey survey = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Survey with Id" + id + "not found"));
        return mapper.toDto(survey);
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
