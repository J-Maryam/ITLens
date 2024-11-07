package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;
import org.youcode.itlens.survey.application.dto.response.SubjectResponseDto;
import org.youcode.itlens.survey.application.mapper.SubjectMapper;
import org.youcode.itlens.survey.application.service.SubjectService;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.youcode.itlens.survey.domain.repository.SubjectRepository;
import org.youcode.itlens.survey.domain.repository.SurveyEditionRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository repository;
    private final SurveyEditionRepository surveyEditionRepository;

    private final SubjectMapper mapper;

    @Override
    public List<SubjectResponseDto> getAll() {
        return repository.findAll()
                .stream().map(mapper::toDto)
                .toList();
    }

    public List<SubjectResponseDto> findAllBySurveyEdition(Long surveyEditionId) {
        if (surveyEditionRepository.existsById(surveyEditionId))
            throw new IllegalArgumentException("SurveyEdition with Id " + surveyEditionId + " not found");

        List<Subject> subjects = repository.findAllBySurveyEditionId(surveyEditionId);
        return subjects.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SubjectResponseDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Subject with id " + id + " not found"));
    }

    @Override
    public SubjectResponseDto create(SubjectRequestDto requestDto) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(requestDto.surveyEditionId())
                .orElseThrow(() -> new IllegalArgumentException("SurveyEdition with Id" + requestDto.surveyEditionId() + " not found"));

        boolean titleExists = repository.existsByTitleAndSurveyEdition(requestDto.title(), surveyEdition);
        if (!titleExists) {
            throw new IllegalArgumentException("Subject with title '" + requestDto.title() + "' already exists in this SurveyEdition.");
        }

        Subject parentSubject = null;

        Subject subject = mapper.toEntity(requestDto)
                .setSurveyEdition(surveyEdition);

        if (requestDto.parentSubjectId() != null) {
            parentSubject = repository.findById(requestDto.parentSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent subject with Id " + requestDto.parentSubjectId() + " not found"));
            subject.setParentSubject(parentSubject);
        }
        subject = repository.save(subject);
        return mapper.toDto(subject);
    }

    @Override
    public SubjectResponseDto update(Long id, SubjectRequestDto requestDto) {
        Subject existingSubject = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject with Id " + id + " not found"));

        SurveyEdition surveyEdition = surveyEditionRepository.findById(requestDto.surveyEditionId())
                .orElseThrow(() -> new IllegalArgumentException("SurveyEdition with Id " + requestDto.surveyEditionId() + " not found"));

        boolean titleExists = repository.existsByTitleAndSurveyEdition(requestDto.title(), surveyEdition);
        if (titleExists && !existingSubject.getTitle().equals(requestDto.title())) {
            throw new IllegalArgumentException("A Subject with title '" + requestDto.title() + "' already exists in this SurveyEdition.");
        }
        existingSubject.setTitle(requestDto.title())
                .setSurveyEdition(surveyEdition);
        existingSubject = repository.save(existingSubject);
        return mapper.toDto(existingSubject);
    }

    @Override
    public void delete(Long aLong) {

    }
}
