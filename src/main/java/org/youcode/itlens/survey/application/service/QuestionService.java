package org.youcode.itlens.survey.application.service;

import org.youcode.itlens.common.application.service.GenericService;
import org.youcode.itlens.survey.application.dto.request.QuestionRequestDto;
import org.youcode.itlens.survey.application.dto.response.QuestionResponseDto;
import org.youcode.itlens.survey.domain.entities.Question;

public interface QuestionService extends GenericService<Question, Long, QuestionRequestDto, QuestionResponseDto> {
}
