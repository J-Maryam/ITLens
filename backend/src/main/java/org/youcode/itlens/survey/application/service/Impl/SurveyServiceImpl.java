package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.application.dto.PagedResponse;
import org.youcode.itlens.common.domain.exception.ConflictException;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
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
    public PagedResponse<SurveyResponseDto> getAll(Pageable pageable) {
        Page<Survey> surveyPage = repository.findAll(pageable);
        List<SurveyResponseDto> surveys = surveyPage.getContent().stream().map(mapper::toDto).toList();
        return new PagedResponse<>(
                surveys,
                surveyPage.getNumber(),
                surveyPage.getSize(),
                surveyPage.getTotalElements(),
                surveyPage.getTotalPages(),
                surveyPage.isLast()
        );
    }

    @Override
    public SurveyResponseDto getById(Long id) {
        Survey survey = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Survey with Id " + id + " not found"));
        return mapper.toDto(survey);
    }

    @Override
    public SurveyResponseDto create(SurveyRequestDto surveyRequestDto) {
        if (repository.existsByTitle(surveyRequestDto.title()))
            throw new ConflictException("Survey title already exists");

        Owner owner = ownerRepository.findById(surveyRequestDto.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner with Id " + surveyRequestDto.ownerId() + " not found"));

        Survey survey = mapper.toEntity(surveyRequestDto);
        survey.setOwner(owner);
        survey = repository.save(survey);
        return mapper.toDto(survey);
    }

    @Override
    public SurveyResponseDto update(Long id, SurveyRequestDto requestDto) {
        Survey existingSurvey = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Survey with Id " + id + " not found"));

        Owner owner = ownerRepository.findById(requestDto.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + requestDto.ownerId() + " not found"));

        if (repository.existsByTitle(requestDto.title()))
            throw new ConflictException("Survey title already exists");

        existingSurvey.setTitle(requestDto.title())
                .setDescription(requestDto.description())
                .setOwner(owner);

        existingSurvey = repository.save(existingSurvey);

        return mapper.toDto(existingSurvey);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Survey with Id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
