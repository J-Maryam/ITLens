package org.youcode.itlens.owner.application.service;

import org.youcode.itlens.common.application.service.GenericService;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.domain.Owner;

public interface OwnerService extends GenericService<Owner, Long, OwnerRequestDTO, OwnerResponseDTO> {
}
