package org.youcode.itlens.survey.application.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.youcode.itlens.survey.domain.entities.Survey;

import java.time.LocalDate;

public record SurveyEditionRequest(
        @Temporal(TemporalType.DATE)
        @Column(nullable = false)
        LocalDate creationDate,

        @Temporal(TemporalType.DATE)
        @Column(nullable = false)
        LocalDate startDate,

        @Column(nullable = false)
        int year,

        @ManyToOne
        Survey survey
) {
}
