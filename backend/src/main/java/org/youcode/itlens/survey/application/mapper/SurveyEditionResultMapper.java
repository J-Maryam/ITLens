//package org.youcode.itlens.survey.application.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.youcode.itlens.survey.application.dto.response.SurveyEditionResultResponseDTO;
//import org.youcode.itlens.survey.domain.entities.Subject;
//import org.youcode.itlens.survey.domain.entities.SurveyEdition;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface SurveyEditionResultMapper {
//
//    @Mapping(target = "surveyTitle", source = "survey.title")
//    @Mapping(target = "description", source = "survey.description")
//    @Mapping(target = "year", source = "year")
//    @Mapping(target = "chapters", source = "chapters")
//    SurveyEditionResultResponseDTO toDto(SurveyEdition surveyEdition);
//
//    List<SurveyEditionResultResponseDTO.ChapterResultDTO> toChapterDtoList(List<Subject> chapters);
//
//    @Mapping(target = "title", source = "title")
//    @Mapping(target = "subChapters", source = "subChapters")
//    @Mapping(target = "questions", source = "questions")
//    SurveyEditionResultResponseDTO.ChapterResultDTO toChapterDto(Subject chapter);
//}
