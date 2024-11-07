package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.SurveyEditionRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.application.mapper.SurveyEditionMapper;
import org.youcode.itlens.survey.application.service.SurveyEditionService;
import org.youcode.itlens.survey.domain.repository.SurveyEditionRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SurveyEditionServiceImpl implements SurveyEditionService {

    private final SurveyEditionRepository repository;
    private final SurveyEditionMapper mapper;

    @Override
    public List<SurveyEditionResponseDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SurveyEditionResponseDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition with Id " + id + "not found"));
    }

    @Override
    public SurveyEditionResponseDto create(SurveyEditionRequestDto surveyEditionRequestDto) {
        return null;
    }

    @Override
    public SurveyEditionResponseDto update(Long aLong, SurveyEditionRequestDto surveyEditionRequestDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
