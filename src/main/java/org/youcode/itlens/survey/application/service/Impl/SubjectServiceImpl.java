package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;
import org.youcode.itlens.survey.application.dto.response.SubjectResponseDto;
import org.youcode.itlens.survey.application.mapper.SubjectMapper;
import org.youcode.itlens.survey.application.service.SubjectService;
import org.youcode.itlens.survey.domain.repository.SubjectRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository repository;
    private final SubjectMapper mapper;

    @Override
    public List<SubjectResponseDto> getAll() {
        return List.of();
    }

    @Override
    public SubjectResponseDto getById(Long aLong) {
        return null;
    }

    @Override
    public SubjectResponseDto create(SubjectRequestDto subjectRequestDto) {
        return null;
    }

    @Override
    public SubjectResponseDto update(Long aLong, SubjectRequestDto subjectRequestDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
