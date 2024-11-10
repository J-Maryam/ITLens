package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.answerResponse.*;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResponseDto;
import org.youcode.itlens.survey.application.service.QuestionService;
import org.youcode.itlens.survey.application.service.SurveyEditionService;
import org.youcode.itlens.survey.application.service.SurveyParticipationService;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.youcode.itlens.survey.domain.entities.enums.QuestionType;
import org.youcode.itlens.survey.domain.repository.AnswerRepository;
import org.youcode.itlens.survey.domain.repository.SurveyEditionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    private final SurveyEditionService surveyEditionService;

    @Override
    public void participate(Long surveyEditionId, SurveyParticipationRequest request) {
        if (request == null || request.responses() == null || request.responses().isEmpty()) {
            throw new IllegalArgumentException("Request or responses must not be null or empty.");
        }

        SurveyEditionResponseDto surveyEdition = findSurveyEditionBySurveyId(surveyEditionId);
        validateSurveyEditionDate(surveyEdition);

        request.responses().forEach(responseDTO -> {
            Long questionId = extractQuestionId(responseDTO);
            QuestionResponseDto question = findQuestionById(questionId);
            processResponse(responseDTO, question);
        });
    }

    private SurveyEditionResponseDto findSurveyEditionBySurveyId(Long surveyEditionId) {
        return surveyEditionService.getById(surveyEditionId);
    }

    private void validateSurveyEditionDate(SurveyEditionResponseDto surveyEdition) {
        LocalDate now = LocalDate.now();
        LocalDate endDate = LocalDate.of(surveyEdition.year(), 12, 31);

        if (now.isBefore(surveyEdition.startDate()) && now.isAfter(endDate)) {
            throw new IllegalArgumentException("Participation is only allowed during the active period of the survey edition.");
        }
    }

    private Long extractQuestionId(ResponseDTO responseDTO) {
        if (responseDTO instanceof SingleAnswerResponseDTO singleAnswer) {
            return singleAnswer.questionId();
        } else if (responseDTO instanceof MultipleAnswersResponseDTO multipleAnswer) {
            return multipleAnswer.questionId();
        } else if (responseDTO instanceof RangeAnswerResponseDTO rangeAnswer) {
            return rangeAnswer.questionId();
        }
        throw new IllegalArgumentException("Unknown response type: " + responseDTO.getClass());
    }

    private void processResponse(ResponseDTO responseDTO, QuestionResponseDto question) {
        if (question.questionType() == QuestionType.SINGLE_CHOICE && responseDTO instanceof SingleAnswerResponseDTO singleAnswer) {
            saveSingleAnswerResponse(singleAnswer);
        } else if (question.questionType() == QuestionType.MULTIPLE_CHOICE && responseDTO instanceof MultipleAnswersResponseDTO multipleAnswer) {
            saveMultipleAnswersResponse(multipleAnswer);
        } else if (responseDTO instanceof RangeAnswerResponseDTO rangeAnswer) {
            saveRangeAnswerResponse(rangeAnswer);
        } else {
            throw new IllegalArgumentException("Invalid response type for question type: " + question.questionType());
        }
    }

    private void saveSingleAnswerResponse(SingleAnswerResponseDTO responseDTO) {
        Answer answer = findAnswerById(responseDTO.answerId());
        incrementAndSaveAnswer(answer);
    }

    private void saveMultipleAnswersResponse(MultipleAnswersResponseDTO responseDTO) {
        List<Answer> answers = answerRepository.findAllById(responseDTO.answersId());
        validateAnswersExist(answers, responseDTO.answersId());
        answers.forEach(this::incrementAndSaveAnswer);
    }

    private void saveRangeAnswerResponse(RangeAnswerResponseDTO responseDTO) {
        String[] rangeParts = responseDTO.answerRange().split("-");
        validateRangeFormat(rangeParts);

        long startId = Long.parseLong(rangeParts[0]);
        long endId = Long.parseLong(rangeParts[1]);

        List<Answer> answers = answerRepository.findAllByIdInRange(startId, endId);
        if (answers.size() != (endId - startId + 1)) {
            throw new EntityNotFoundException("One or more answers in the specified range were not found.");
        }
        answers.forEach(this::incrementAndSaveAnswer);
    }

    private void incrementAndSaveAnswer(Answer answer) {
        answer.incrementSelectionCount();
        answerRepository.save(answer);
    }

    private void validateAnswersExist(List<Answer> answers, List<Long> answerIds) {
        if (answers.size() != answerIds.size()) {
            throw new EntityNotFoundException("One or more answers not found for the provided IDs.");
        }
    }

    private void validateRangeFormat(String[] rangeParts) {
        if (rangeParts.length != 2) {
            throw new IllegalArgumentException("Invalid range format.");
        }
        long startId = Long.parseLong(rangeParts[0]);
        long endId = Long.parseLong(rangeParts[1]);
        if (startId > endId) {
            throw new IllegalArgumentException("Start ID must be less than or equal to End ID in range.");
        }
    }

    private QuestionResponseDto findQuestionById(Long questionId) {
        return questionService.getById(questionId);
    }

    private Answer findAnswerById(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("No answer found for ID: " + answerId));
    }

}
