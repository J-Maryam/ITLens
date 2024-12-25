package org.youcode.itlens.owner.application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record OwnerRequestDTO(
        @NotBlank
        @Column(unique=true)
        String name
) {
}
