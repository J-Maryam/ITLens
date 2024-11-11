package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.survey.application.dto.request.SurveyEditionRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.application.service.SurveyEditionService;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/survey-editions")
public class SurveyEditionController {
    private final SurveyEditionService service;

    @GetMapping
    public ResponseEntity<List<SurveyEditionResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<SurveyEditionResponseDto> surveyEditions = service.getAll(pageable);
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

    @PutMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDto> update(@PathVariable Long id, @RequestBody @Valid SurveyEditionRequestDto dto) {
        SurveyEditionResponseDto updatedSurveyEdition = service.update(id, dto);
        return ResponseEntity.ok(updatedSurveyEdition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
