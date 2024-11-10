package org.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Integer selectionCount = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;

    public void incrementSelectionCount() {
        this.selectionCount++;
    }
}
