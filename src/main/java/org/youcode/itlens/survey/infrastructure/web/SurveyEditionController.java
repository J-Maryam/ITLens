package org.youcode.itlens.survey.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.itlens.survey.application.service.SurveyEditionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/survey-editions")
public class SurveyEditionController {
    private final SurveyEditionService service;
}
