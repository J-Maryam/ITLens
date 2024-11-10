package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.answerResponse.*;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.service.QuestionService;
import org.youcode.itlens.survey.application.service.SurveyParticipationService;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.repository.AnswerRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

    @Override
    public void participate(String surveyId, SurveyParticipationRequest request) {
        request.responses().forEach(responseDTO -> {
            Long questionId = extractQuestionId(responseDTO);
            QuestionResponseDto question = findQuestionById(questionId) ;
            processResponse(responseDTO, question);
        });
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
        if (responseDTO instanceof SingleAnswerResponseDTO singleAnswer) {
            saveSingleAnswerResponse(singleAnswer);
        } else if (responseDTO instanceof MultipleAnswersResponseDTO multipleAnswer) {
            saveMultipleAnswersResponse(multipleAnswer);
        } else if (responseDTO instanceof RangeAnswerResponseDTO rangeAnswer) {
            saveRangeAnswerResponse(rangeAnswer);
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
            throw new EntityNotFoundException("Invalid range format.");
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
