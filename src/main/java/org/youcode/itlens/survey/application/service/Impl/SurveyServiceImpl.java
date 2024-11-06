package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.owner.application.service.OwnerService;
import org.youcode.itlens.owner.application.service.OwnerServiceImpl;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.owner.domain.OwnerRepository;
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
    private final OwnerRepository ownerRepository;
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
        Survey survey = mapper.toEntity(surveyRequestDto);
        survey = repository.save(survey);
        return mapper.toDto(survey);
    }

    @Override
    public SurveyResponseDto update(Long id, SurveyRequestDto requestDto) {
        Survey existingSurvey = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Survey with Id " + id + " not found"));

        Owner owner = ownerRepository.findById(requestDto.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + requestDto.ownerId() + " not found"));

        existingSurvey.setTitle(requestDto.title())
                .setDescription(requestDto.description())
                .setOwner(owner);

        existingSurvey = repository.save(existingSurvey);

        return mapper.toDto(existingSurvey);
    }


    @Override
    public void delete(Long aLong) {

    }
}
