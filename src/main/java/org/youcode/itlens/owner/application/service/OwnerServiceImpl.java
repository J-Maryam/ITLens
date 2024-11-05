package org.youcode.itlens.owner.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.application.mapper.OwnerMapper;
import org.youcode.itlens.owner.domain.OwnerRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository repository;
    private final OwnerMapper mapper;

    @Override
    public List<OwnerResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public OwnerResponseDTO getById(Long aLong) {
        return null;
    }

    @Override
    public OwnerResponseDTO create(OwnerRequestDTO ownerRequestDTO) {
        return null;
    }

    @Override
    public OwnerResponseDTO update(Long aLong, OwnerRequestDTO ownerRequestDTO) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
