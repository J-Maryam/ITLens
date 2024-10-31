package org.youcode.itlens.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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
