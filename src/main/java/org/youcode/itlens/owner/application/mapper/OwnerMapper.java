package org.youcode.itlens.owner.application.mapper;

import org.mapstruct.Mapper;
import org.youcode.itlens.common.application.mapper.GenericMapper;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.domain.Owner;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends GenericMapper<Owner, OwnerRequestDTO, OwnerResponseDTO> {
}
