package org.youcode.itlens.survey.application.service.Impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.QuestionRequestDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.mapper.QuestionMapper;
import org.youcode.itlens.survey.application.service.QuestionService;
import org.youcode.itlens.survey.application.service.SubjectService;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.repository.QuestionRepository;
import org.youcode.itlens.survey.domain.repository.SubjectRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;
    private final SubjectRepository subjectRepository;
    private QuestionMapper mapper;

    @Override
    public List<QuestionResponseDto> getAll() {
        return List.of();
    }

    @Override
    public QuestionResponseDto getById(Long aLong) {
        return null;
    }

    @Override
    public QuestionResponseDto create(QuestionRequestDto requestDto) {
        return null;
    }

    @Override
    public QuestionResponseDto update(Long aLong, QuestionRequestDto questionRequestDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
