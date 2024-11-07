package org.youcode.itlens.survey.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
