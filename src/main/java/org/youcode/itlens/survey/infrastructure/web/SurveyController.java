package org.youcode.itlens.survey.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
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
    }}
