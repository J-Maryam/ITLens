package org.youcode.itlens.owner.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;
import org.youcode.itlens.common.application.validation.annotation.Exists;
import org.youcode.itlens.common.application.validation.annotation.UniqueValue;
import org.youcode.itlens.survey.domain.entities.Survey;

import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Exists(entityClass = Owner.class)
    private Long id;

    @NotBlank
    @UniqueValue(entityClass = Owner.class, fieldName = "name", message = "name already exists (should be unique), try another name")
    @Column(unique=true, nullable=false)
    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Survey> surveys;

}
