package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.common.application.dto.PagedResponse;
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
    public ResponseEntity<PagedResponse<QuestionResponseDto>> getAllQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<QuestionResponseDto> questions = service.getAll(pageable);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getById(@PathVariable Long id) {
        QuestionResponseDto question = service.getById(id);
        return ResponseEntity.ok((question));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
