package org.youcode.itlens.survey.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;
import org.youcode.itlens.survey.application.service.SurveyEditionResultService;

@RestController
@RequiredArgsConstructor
public class SurveyEditionResultController {
    private final SurveyEditionResultService surveyEditionResultService;

    @GetMapping("/surveys/{surveyId}/results")
    public SurveyEditionResultResponseDTO getSurveyResults(@PathVariable Long surveyId) {
        return surveyEditionResultService.getResults(surveyId);
    }
}
