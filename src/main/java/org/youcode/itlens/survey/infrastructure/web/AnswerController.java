package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.survey.application.dto.request.AnswerRequestDto;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.application.service.AnswerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/answers")
public class AnswerController {
    private final AnswerService service;

    @GetMapping
    public ResponseEntity<List<AnswerResponseDto>> getAll() {
        List<AnswerResponseDto> answers = service.getAll();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> getById(@PathVariable Long id) {
        AnswerResponseDto answer = service.getById(id);
        return ResponseEntity.ok(answer);
    }

    @PostMapping
    public ResponseEntity<AnswerResponseDto> create(@RequestBody @Valid AnswerRequestDto requestDto) {
        AnswerResponseDto created = service.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> update(@PathVariable Long id, @RequestBody @Valid AnswerRequestDto requestDto) {
        AnswerResponseDto updated = service.update(id, requestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
