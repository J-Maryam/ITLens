package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.common.application.dto.PagedResponse;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.service.SurveyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/surveys")
public class SurveyController {
    private final SurveyService service;

    @GetMapping
    public ResponseEntity<PagedResponse<SurveyResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<SurveyResponseDto> surveys = service.getAll(pageable);
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> findById(@PathVariable Long id) {
        SurveyResponseDto survey = service.getById(id);
        return ResponseEntity.ok(survey);
    }

    @PostMapping
    public ResponseEntity<SurveyResponseDto> create(@RequestBody @Valid SurveyRequestDto dto) {
        SurveyResponseDto newSurvey = service.create(dto);
        return new ResponseEntity<>(newSurvey, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> update(@PathVariable Long id, @RequestBody @Valid SurveyRequestDto dto) {
        SurveyResponseDto updatedSurvey = service.update(id, dto);
        return ResponseEntity.ok(updatedSurvey);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
