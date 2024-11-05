package org.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String text;

    private Integer answerCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
