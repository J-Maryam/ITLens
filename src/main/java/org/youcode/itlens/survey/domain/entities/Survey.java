package org.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.owner.domain.Owner;

import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Exists(entityClass = Survey.class)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany
    @JoinColumn(name = "survey_edition_id")
    private List<SurveyEdition> surveyEditions;
}