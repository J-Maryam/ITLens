package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.survey.application.dto.request.SurveyEditionRequestDto;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.service.SurveyEditionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/survey-editions")
public class SurveyEditionController {
    private final SurveyEditionService service;

    @GetMapping
    public ResponseEntity<List<SurveyEditionResponseDto>> findAll() {
        List<SurveyEditionResponseDto> surveyEditions = service.getAll();
        return ResponseEntity.ok(surveyEditions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDto> findById(@PathVariable Long id) {
        SurveyEditionResponseDto surveyEdition = service.getById(id);
        return ResponseEntity.ok(surveyEdition);
    }

    @PostMapping
    public ResponseEntity<SurveyEditionResponseDto> create(@RequestBody @Valid SurveyEditionRequestDto dto) {
        SurveyEditionResponseDto newSurveyEdition = service.create(dto);
        return new ResponseEntity<>(newSurveyEdition, HttpStatus.CREATED);
    }
}
