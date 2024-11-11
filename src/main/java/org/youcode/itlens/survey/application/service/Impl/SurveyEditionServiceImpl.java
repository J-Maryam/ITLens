package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.SurveyEditionRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.application.mapper.SurveyEditionMapper;
import org.youcode.itlens.survey.application.service.SurveyEditionService;
import org.youcode.itlens.survey.domain.entities.Survey;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.youcode.itlens.survey.domain.repository.SurveyEditionRepository;
import org.youcode.itlens.survey.domain.repository.SurveyRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SurveyEditionServiceImpl implements SurveyEditionService {

    private final SurveyEditionRepository repository;
    private final SurveyEditionMapper mapper;
    private final SurveyRepository surveyRepository;

    @Override
    public List<SurveyEditionResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).stream()
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
    public SurveyEditionResponseDto create(SurveyEditionRequestDto requestDto) {
        Survey survey = surveyRepository.findById(requestDto.surveyId())
                .orElseThrow(() -> new EntityNotFoundException("Survey with Id " + requestDto.surveyId() + " not found"));
        SurveyEdition surveyEdition = mapper.toEntity(requestDto)
                .setSurvey(survey);
        surveyEdition = repository.save(surveyEdition);
        return mapper.toDto(surveyEdition);
    }

    @Override
    public SurveyEditionResponseDto update(Long id, SurveyEditionRequestDto requestDto) {
        SurveyEdition existingSurveyEdition = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition with Id " + id + " not found"));
        Survey survey = surveyRepository.findById(requestDto.surveyId())
                .orElseThrow(() -> new EntityNotFoundException("Survey with Id " + requestDto.surveyId() + " not found"));

        existingSurveyEdition.setCreationDate(requestDto.creationDate())
                .setStartDate(requestDto.startDate())
                .setYear(requestDto.year())
                .setSurvey(survey);
        repository.save(existingSurveyEdition);
        return mapper.toDto(existingSurveyEdition);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Survey Edition with Id " + id + " not found");
        repository.deleteById(id);
    }
}
