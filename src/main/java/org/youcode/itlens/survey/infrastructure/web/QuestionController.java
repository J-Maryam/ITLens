package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.survey.application.dto.request.QuestionRequestDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/questions")
public class QuestionController {
    private final QuestionService service;

    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {
        List<QuestionResponseDto> questions = service.getAll();
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<QuestionResponseDto> create(@RequestBody @Valid QuestionRequestDto requestDto) {
        QuestionResponseDto create = service.create(requestDto);
        return ResponseEntity.ok(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> update(@PathVariable Long id, @RequestBody @Valid QuestionRequestDto requestDto) {
        QuestionResponseDto update = service.update(id, requestDto);
        return  ResponseEntity.ok(update);
    }
}
