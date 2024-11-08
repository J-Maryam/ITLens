package org.youcode.itlens.survey.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.itlens.survey.application.dto.request.AnswerRequestDto;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.application.service.AnswerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/answers")
public class AnswerController {
    private final AnswerService service;

    @PostMapping
    public ResponseEntity<AnswerResponseDto> create(AnswerRequestDto requestDto) {
        AnswerResponseDto created = service.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
