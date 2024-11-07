package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.itlens.survey.application.dto.request.SubjectRequestDto;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SubjectResponseDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.service.SubjectService;

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
}
