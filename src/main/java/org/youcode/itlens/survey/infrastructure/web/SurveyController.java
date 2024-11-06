package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
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
    public ResponseEntity<List<SurveyResponseDto>> findAll() {
        List<SurveyResponseDto> surveys = service.getAll();
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
}
