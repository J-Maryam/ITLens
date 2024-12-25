package org.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent(message = "La date de création doit être dans le passé ou aujourd'hui.")
    @NotNull
    private LocalDate creationDate;

    @Future(message = "La date de début doit être dans le futur.")
    @NotNull
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private int year;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveyEdition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();
}
