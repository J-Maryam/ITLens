package org.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    private Integer id;

    @Column(unique=true, nullable=false)
    private String title;

    @ManyToOne
    private SurveyEdition surveyEdition;

    @ManyToOne
    private Subject parentSubject;

    @OneToMany
    private List<Question> questions;
}
