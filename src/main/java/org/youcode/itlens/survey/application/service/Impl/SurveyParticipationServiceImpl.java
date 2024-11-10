package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.answerResponse.MultipleAnswersResponseDTO;
import org.youcode.itlens.survey.application.dto.request.answerResponse.SingleAnswerResponseDTO;
import org.youcode.itlens.survey.application.dto.request.answerResponse.SurveyParticipationRequest;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.application.service.AnswerService;
import org.youcode.itlens.survey.application.service.QuestionService;
import org.youcode.itlens.survey.application.service.SurveyParticipationService;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.entities.Question;
import org.youcode.itlens.survey.domain.repository.AnswerRepository;
import org.youcode.itlens.survey.domain.repository.QuestionRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

//    private final QuestionService questionService;
//    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void participate(String surveyId, SurveyParticipationRequest request) {

    }

    private void saveSingleAnswerResponse(SingleAnswerResponseDTO responseDTO) {
        Question question = questionRepository.findById(responseDTO.questionId())
                .orElseThrow(() -> new EntityNotFoundException("Question " + responseDTO.questionId() + " not found"));
        Answer answer = answerRepository.findById(responseDTO.answerId())
                .orElseThrow(() -> new EntityNotFoundException("Answer " + responseDTO.answerId() + " not found"));
        answer.incrementSelectionCount();
        answerRepository.save(answer);
    }

    private void saveMultipleAnswersResponse(MultipleAnswersResponseDTO responseDTO) {
        Question question = questionRepository.findById(responseDTO.questionId())
                .orElseThrow(() -> new EntityNotFoundException("Question " + responseDTO.questionId() + " not found"));

        responseDTO.answersId().forEach(answerId -> {
            Answer answer = answerRepository.findById(answerId)
                    .orElseThrow(() -> new EntityNotFoundException("Answer " + answerId + " not found"));
            answer.incrementSelectionCount();
            answerRepository.save(answer);
        });
    }
}
