package org.youcode.itlens.survey.application.service.impl;

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
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public AnswerResponseDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Answer with Id " + id + " not found"));
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
    public AnswerResponseDto update(Long id, AnswerRequestDto requestDto) {
        Answer answer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer with Id " + id + " not found"));

        Question question = questionRepository.findById(requestDto.questionId())
                .orElseThrow(() -> new EntityNotFoundException("Question with Id " + requestDto.questionId() + " not found"));

        answer.setText(requestDto.text())
                .setQuestion(question);
        answer = repository.save(answer);
        return mapper.toDto(answer);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Answer with Id " + id + " not found");
        }
        repository.deleteById(id);
    }
}