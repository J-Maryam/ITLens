package org.youcode.itlens.survey.application.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.owner.domain.OwnerRepository;
import org.youcode.itlens.survey.application.dto.request.SurveyRequestDto;
import org.youcode.itlens.survey.application.dto.response.SurveyResponseDto;
import org.youcode.itlens.survey.application.mapper.SurveyMapper;
import org.youcode.itlens.survey.domain.entities.Survey;
import org.youcode.itlens.survey.domain.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private OwnerRepository ownerRepository;

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

        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(mapper.toEntity(request)).thenReturn(survey);
        when(surveyRepository.save(any(Survey.class))).thenReturn(survey);
        when(mapper.toDto(survey)).thenReturn(expectedResponse);

        SurveyResponseDto response = surveyService.create(request);

        assertEquals(expectedResponse, response);
        verify(surveyRepository, times(1)).save(any(Survey.class));
        verify(mapper, times(1)).toEntity(request);
        verify(mapper, times(1)).toDto(survey);
        verify(ownerRepository, times(1)).findById(owner.getId());
    }

    @Test
    void shouldGetSurveyByIdWhenExists() {
        Owner owner = new Owner(1L, "Owner Name", List.of());
        Long surveyId = 1L;
        Survey survey = new Survey(surveyId, "Survey Title", "Survey Description", owner, List.of());
        SurveyResponseDto expectedResponse = new SurveyResponseDto(surveyId, "Survey Title", "Survey Description", null, List.of());

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));
        when(mapper.toDto(survey)).thenReturn(expectedResponse);

        SurveyResponseDto response = surveyService.getById(surveyId);

        assertEquals(expectedResponse, response);
        verify(surveyRepository, times(1)).findById(surveyId);
        verify(mapper, times(1)).toDto(survey);
    }

    @Test
    void shouldThrowExceptionWhenSurveyNotFoundById() {
        Long surveyId = 1L;

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> surveyService.getById(surveyId));
        verify(surveyRepository, times(1)).findById(surveyId);
    }

    @Test
    void shouldUpdateSurveySuccessfully() {
        Owner owner = new Owner(1L, "Owner Name", List.of());
        Long surveyId = 1L;
        SurveyRequestDto request = new SurveyRequestDto("Updated Title", "Updated Description", owner.getId());
        Survey survey = new Survey(surveyId, "Survey Title", "Survey Description", owner, List.of());
        Survey updatedSurvey = new Survey(surveyId, "Updated Title", "Updated Description", owner, List.of());
        SurveyResponseDto expectedResponse = new SurveyResponseDto(surveyId, "Updated Title", "Updated Description", null, List.of());

        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));
        when(surveyRepository.save(any(Survey.class))).thenReturn(updatedSurvey);
        when(mapper.toDto(updatedSurvey)).thenReturn(expectedResponse);

        SurveyResponseDto response = surveyService.update(surveyId, request);

        assertEquals(expectedResponse, response);
        verify(surveyRepository, times(1)).findById(surveyId);
        verify(surveyRepository, times(1)).save(any(Survey.class));
        verify(mapper, times(1)).toDto(updatedSurvey);
        verify(ownerRepository, times(1)).findById(owner.getId());
    }

    @Test
    void shouldDeleteSurveySuccessfully() {
        Long surveyId = 1L;

        when(surveyRepository.existsById(surveyId)).thenReturn(true);
        doNothing().when(surveyRepository).deleteById(surveyId);
        surveyService.delete(surveyId);
        verify(surveyRepository, times(1)).deleteById(surveyId);
    }

    @Test
    void shouldThrowExceptionWhenSurveyNotFound() {
        Long surveyId = 1L;

        when(surveyRepository.existsById(surveyId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> {
            surveyService.delete(surveyId);
        });
    }

    @Test
    void shouldGetAllSurveysPaged() {
        Owner owner = new Owner(1L, "Owner Name", List.of());
        Survey survey = new Survey(1L, "Survey Title", "Survey Description", owner, List.of());
        SurveyResponseDto surveyDto = new SurveyResponseDto(1L, "Survey Title", "Survey Description", null, List.of());

        Pageable pageable = PageRequest.of(0, 10);
        Page<Survey> surveyPage = new PageImpl<>(List.of(survey), pageable, 1);

        when(surveyRepository.findAll(pageable)).thenReturn(surveyPage);
        when(mapper.toDto(survey)).thenReturn(surveyDto);

        var response = surveyService.getAll(pageable);

        assertEquals(1, response.content().size());
        assertEquals(surveyDto, response.content().get(0));
        verify(surveyRepository, times(1)).findAll(pageable);
        verify(mapper, times(1)).toDto(survey);
    }
}