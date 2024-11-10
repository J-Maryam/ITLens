package org.youcode.itlens.survey.application.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.survey.application.dto.request.answerResponse.*;
import org.youcode.itlens.survey.application.service.SurveyParticipationService;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.entities.Question;
import org.youcode.itlens.survey.domain.repository.AnswerRepository;
import org.youcode.itlens.survey.domain.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

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
//        request.responses().forEach(responseDTO -> {
//            Question question = findQuestion(responseDTO.questionId());
//            processResponse(responseDTO, question);
//        });
    }

    private void processResponse(ResponseDTO responseDTO, Question question) {
        if (responseDTO instanceof SingleAnswerResponseDTO singleAnswer) {
            saveSingleAnswerResponse(singleAnswer);
        } else if (responseDTO instanceof MultipleAnswersResponseDTO multipleAnswer) {
            saveMultipleAnswersResponse(multipleAnswer);
        } else if (responseDTO instanceof RangeAnswerResponseDTO rangeAnswer) {
            saveRangeAnswerResponse(rangeAnswer);
        }
    }

    private void saveSingleAnswerResponse(SingleAnswerResponseDTO responseDTO) {
        Answer answer = findAnswer(responseDTO.answerId());
        answer.incrementSelectionCount();
        answerRepository.save(answer);
    }

    private void saveMultipleAnswersResponse(MultipleAnswersResponseDTO responseDTO) {
        List<Answer> answers = answerRepository.findAllById(responseDTO.answersId());
        if (answers.size() != responseDTO.answersId().size()) {
            throw new EntityNotFoundException("One or more answers not found for the provided IDs.");
        }
        answers.forEach(Answer::incrementSelectionCount);
        answerRepository.saveAll(answers);
    }

    private void saveRangeAnswerResponse(RangeAnswerResponseDTO responseDTO) {
        String[] rangeParts = responseDTO.answerRange().split("-");
        if (rangeParts.length != 2) {
            throw new EntityNotFoundException("Invalid range format: " + responseDTO.answerRange());
        }

        long startId = Long.parseLong(rangeParts[0]);
        long endId = Long.parseLong(rangeParts[1]);

        List<Answer> answers = answerRepository.findAllByIdInRange(startId, endId);
        answers.forEach(Answer::incrementSelectionCount);
        answerRepository.saveAll(answers);
    }

    private Question findQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question " + questionId + " not found"));
    }

    private Answer findAnswer(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("Answer " + answerId + " not found"));
    }

}
