package org.youcode.itlens.owner.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.youcode.itlens.common.application.validation.annotation.UniqueValue;
import org.youcode.itlens.survey.domain.entities.Survey;

import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @UniqueValue(entityClass = Owner.class, fieldName = "name")
    @Column(unique=true, nullable=false)
    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Survey> surveys;

}
