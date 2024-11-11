package org.youcode.itlens.survey.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;
import org.youcode.itlens.survey.domain.entities.Answer;
import org.youcode.itlens.survey.domain.entities.Question;
import org.youcode.itlens.survey.domain.entities.Subject;
import org.youcode.itlens.survey.domain.entities.SurveyEdition;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyEditionResultMapper {

    @Mapping(source = "survey.title", target = "surveyTitle")
    @Mapping(source = "survey.description", target = "description")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "subjects", target = "chapters", qualifiedByName = "mapChapters")
    SurveyEditionResultResponseDTO mapSurveyToSurveyEditionResultResponse(SurveyEdition survey);

    @Named("mapChapters")
    default List<SurveyEditionResultResponseDTO.ChapterResultDTO> mapChapters(List<Subject> chapters) {
        return chapters.stream()
                .map(chapter -> new SurveyEditionResultResponseDTO.ChapterResultDTO(
                        chapter.getTitle(),
                        mapSubChapters(chapter.getSubSubjects()),
                        mapQuestions(chapter.getQuestions())
                ))
                .toList();
    }

    @Named("mapSubChapters")
    default List<SurveyEditionResultResponseDTO.ChapterResultDTO> mapSubChapters(List<Subject> subChapters) {
        return subChapters.stream()
                .map(subChapter -> new SurveyEditionResultResponseDTO.ChapterResultDTO(
                        subChapter.getTitle(),
                        mapSubChapters(subChapter.getSubSubjects()),
                        mapQuestions(subChapter.getQuestions())
                ))
                .toList();
    }

    @Named("mapQuestions")
    default List<SurveyEditionResultResponseDTO.QuestionResultDTO> mapQuestions(List<Question> questions) {
        return questions.stream()
                .map(question -> new SurveyEditionResultResponseDTO.QuestionResultDTO(
                        question.getText(),
                        mapAnswers(question.getAnswers()),
                        question.getAnswerCount()
                ))
                .toList();
    }

    @Named("mapAnswers")
    default List<SurveyEditionResultResponseDTO.AnswerDTO> mapAnswers(List<Answer> answers) {
        return answers.stream()
                .map(answer -> new SurveyEditionResultResponseDTO.AnswerDTO(
                        answer.getText(),
                        answer.getSelectionCount()
                ))
                .toList();
    }
}
