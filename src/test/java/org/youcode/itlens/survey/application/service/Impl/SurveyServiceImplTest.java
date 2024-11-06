package org.youcode.itlens.survey.application.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.mapper.SurveyMapper;
import org.youcode.itlens.survey.domain.entities.Survey;
import org.youcode.itlens.survey.domain.repository.SurveyRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyMapper mapper;

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Test
    void shouldCreateSurveySuccessfully() {
        Owner owner = new Owner(1L, "Owner Name", List.of());

        SurveyRequestDto request = new SurveyRequestDto("Survey Title", "Survey Description", owner.getId());

        Survey survey = new Survey(1L, "Survey Title", "Survey Description", owner, List.of());

        SurveyResponseDto expectedResponse = new SurveyResponseDto(1L, "Survey Title", "Survey Description", null, List.of());

        when(mapper.toEntity(request)).thenReturn(survey);
        when(surveyRepository.save(any(Survey.class))).thenReturn(survey);
        when(mapper.toDto(survey)).thenReturn(expectedResponse);

        SurveyResponseDto response = surveyService.create(request);

        assertEquals(expectedResponse, response);
        verify(surveyRepository, times(1)).save(any(Survey.class));
        verify(mapper, times(1)).toEntity(request);
        verify(mapper, times(1)).toDto(survey);
    }
}