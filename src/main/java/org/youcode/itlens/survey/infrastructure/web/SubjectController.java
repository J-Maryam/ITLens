package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SubjectResponseDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.service.SubjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class SubjectController {

    private final SubjectService service;

    @PostMapping("/subjects")
    public ResponseEntity<SubjectResponseDto> create(@RequestBody @Valid SubjectRequestDto dto) {
        SubjectResponseDto newSubject = service.create(dto);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<SubjectResponseDto> surveys = service.getAll(pageable);
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("/survey-editions/{surveyEditionId}/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjectsBySurveyEdition(@PathVariable Long surveyEditionId) {
        List<SubjectResponseDto> subjects = service.findAllBySurveyEdition(surveyEditionId);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectResponseDto> getSubjectById(@PathVariable Long id) {
        SubjectResponseDto subject = service.getById(id);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<SubjectResponseDto> update(@PathVariable Long id, @RequestBody @Valid SubjectRequestDto requestDto) {
        SubjectResponseDto updated = service.update(id, requestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<SubjectResponseDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
