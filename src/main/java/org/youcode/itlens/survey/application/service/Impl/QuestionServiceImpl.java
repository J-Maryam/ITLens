package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.common.domain.exception.SubjectHasSubSubjectsException;
import org.youcode.itlens.survey.application.dto.request.QuestionRequestDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.mapper.QuestionMapper;
import org.youcode.itlens.survey.application.service.QuestionService;
import org.youcode.itlens.survey.domain.entities.Question;
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
    private final QuestionMapper mapper;

    @Override
    public List<QuestionResponseDto> getAll() {
        return repository.findAll()
                .stream().map(mapper::toDto)
                .toList();
    }

    @Override
    public QuestionResponseDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Question with Id " + id + " not found"));
    }

    @Override
    public QuestionResponseDto create(QuestionRequestDto requestDto) {
        Subject subject = subjectRepository.findById(requestDto.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject with Id " + requestDto.subjectId() + " not found"));

        if (!subject.getSubSubjects().isEmpty()){
            throw new SubjectHasSubSubjectsException("Cannot add a question directly to a Subject with SubSubjects. Please add the question to a specific SubSubject.");
        }

//        boolean questionExists = repository.existsByTextAndSubject(requestDto.getText(), subject);
//        if (questionExists) {
//            throw new EntityExistsException("A question with the same text already exists for this subject.");
//        }
        Question question = mapper.toEntity(requestDto);
        question.setSubject(subject);
        question = repository.save(question);
        return mapper.toDto(question);
    }

    @Override
    public QuestionResponseDto update(Long id, QuestionRequestDto requestDto) {
        Question question = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question with Id " + id + " not found"));

        Subject subject = subjectRepository.findById(requestDto.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject with Id " + requestDto.subjectId() + " not found"));

        if (!subject.getSubSubjects().isEmpty()) {
            throw new SubjectHasSubSubjectsException("Cannot update the question directly on a Subject with SubSubjects. Please choose a specific SubSubject.");
        }

        question.setText(requestDto.text())
                .setSubject(subject)
                .setQuestionType(requestDto.questionType());
        question = repository.save(question);
        return mapper.toDto(question);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Question with Id " + id + " not found");
        repository.deleteById(id);
    }
}
