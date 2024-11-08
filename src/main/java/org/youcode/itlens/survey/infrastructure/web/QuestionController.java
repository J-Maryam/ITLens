package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.survey.application.dto.request.QuestionRequestDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.service.QuestionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/questions")
public class QuestionController {
    private final QuestionService service;

    @PostMapping
    public ResponseEntity<QuestionResponseDto> create(@RequestBody @Valid QuestionRequestDto requestDto) {
        QuestionResponseDto create = service.create(requestDto);
        return ResponseEntity.ok(create);
    }
}