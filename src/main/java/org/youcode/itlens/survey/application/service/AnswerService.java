package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.common.application.service.GenericService;
import org.youcode.itlens.survey.application.dto.request.AnswerRequestDto;
import org.youcode.itlens.survey.application.dto.response.AnswerResponseDto;
import org.youcode.itlens.survey.domain.entities.Answer;

public interface AnswerService extends GenericService<Answer, Long, AnswerRequestDto, AnswerResponseDto> {
}
