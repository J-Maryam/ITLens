package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;
//import org.youcode.itlens.survey.application.mapper.SurveyEditionResultMapper;
import org.youcode.itlens.survey.application.service.SurveyEditionResultService;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.entities.Question;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.youcode.itlens.survey.domain.repository.SurveyEditionRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyEditionResultServiceImpl implements SurveyEditionResultService {
    private final SurveyEditionRepository surveyEditionRepository;
//    private final SurveyEditionResultMapper surveyEditionResultMapper;

    @Override
    public SurveyEditionResultResponseDTO getResults(Long surveyId) {
        // Charger l'entité avec toutes ses relations (chapitres, sous-chapitres, questions, etc.)
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with ID: " + surveyId));

        // Transformer l'entité en DTO
        return new SurveyEditionResultResponseDTO(
                surveyEdition.getSurvey().getTitle(),
                surveyEdition.getSurvey().getDescription(),
                surveyEdition.getYear(),
                surveyEdition.getSubjects() != null ? surveyEdition.getSubjects().stream()
                        .map(this::toChapterDto)
                        .toList() : null
        );
    }

    // Transformer un chapitre en ChapterResultDTO
    private SurveyEditionResultResponseDTO.ChapterResultDTO toChapterDto(Subject chapter) {
        return new SurveyEditionResultResponseDTO.ChapterResultDTO(
                chapter.getTitle(),
                chapter.getSubSubjects() != null ? chapter.getSubSubjects().stream()
                        .map(this::toChapterDto) // Recursive call for sub-chapters
                        .toList() : null,
                chapter.getQuestions() != null ? chapter.getQuestions().stream()
                        .map(this::toQuestionDto)
                        .toList() : null
        );
    }

    // Transformer une question en QuestionResultDTO
    private SurveyEditionResultResponseDTO.QuestionResultDTO toQuestionDto(Question question) {
        return new SurveyEditionResultResponseDTO.QuestionResultDTO(
                question.getText(),
                question.getAnswers() != null ? question.getAnswers().stream()
                        .map(this::toAnswerDto)
                        .toList() : null,
                question.getAnswers() != null ? question.getAnswers().size() : 0
        );
    }

    // Transformer une réponse en AnswerDTO
    private SurveyEditionResultResponseDTO.AnswerDTO toAnswerDto(Answer answer) {
        return new SurveyEditionResultResponseDTO.AnswerDTO(
                answer.getText(),
                answer.getSelectionCount()
        );
    }


}
