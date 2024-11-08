package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.itlens.survey.application.dto.request.AnswerRequestDto;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.application.service.AnswerService;
import org.youcode.itlens.survey.domain.repository.AnswerRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;

    @Override
    public List<AnswerResponseDto> getAll() {
        return List.of();
    }

    @Override
    public AnswerResponseDto getById(Long aLong) {
        return null;
    }

    @Override
    public AnswerResponseDto create(AnswerRequestDto answerRequestDto) {
        return null;
    }

    @Override
    public AnswerResponseDto update(Long aLong, AnswerRequestDto answerRequestDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
