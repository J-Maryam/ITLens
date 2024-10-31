package org.youcode.itlens.dto.Owner;

import org.youcode.itlens.entity.Survey;

import java.util.List;

public record ResponseDTO(
        Integer id,
        String name,
        List<Survey> surveys
) {
}
