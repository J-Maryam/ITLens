package org.youcode.itlens.survey.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.survey.application.dto.request.answerResponse.SurveyParticipationRequest;
import org.youcode.itlens.survey.application.service.SurveyParticipationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/survey-editions")
public class SurveyParticipationController {
    private final SurveyParticipationService service;

    @PostMapping("/{surveyEditionId}/participate")
    public ResponseEntity<Void> participate(@PathVariable Long surveyEditionId, @RequestBody @Valid SurveyParticipationRequest request) {
        service.participate(surveyEditionId, request);
        return ResponseEntity.ok().build();
    }
}
