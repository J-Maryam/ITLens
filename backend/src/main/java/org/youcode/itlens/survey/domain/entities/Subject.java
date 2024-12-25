package org.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne
    private SurveyEdition surveyEdition;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject parentSubject;

    @OneToMany(mappedBy = "parentSubject", fetch = FetchType.EAGER)
    private List<Subject> subSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();
}
