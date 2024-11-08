package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.AnswerRequestDto;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.application.mapper.AnswerMapper;
import org.youcode.itlens.survey.application.service.AnswerService;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.entities.Question;
import org.youcode.itlens.survey.domain.repository.AnswerRepository;
import org.youcode.itlens.survey.domain.repository.QuestionRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper mapper;

    @Override
    public List<AnswerResponseDto> getAll() {
        return List.of();
    }

    @Override
    public AnswerResponseDto getById(Long aLong) {
        return null;
    }

    @Override
    public AnswerResponseDto create(AnswerRequestDto requestDto) {
        Question question = questionRepository.findById(requestDto.questionId())
                .orElseThrow(() -> new EntityNotFoundException("Question with Id " + requestDto.questionId() + " not found"));

        Answer answer = mapper.toEntity(requestDto);
        answer.setQuestion(question);
        answer = repository.save(answer);
        return mapper.toDto(answer);
    }

    @Override
    public AnswerResponseDto update(Long aLong, AnswerRequestDto answerRequestDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
