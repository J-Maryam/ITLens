package org.youcode.itlens.owner.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.common.application.dto.PagedResponse;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.application.mapper.OwnerMapper;
import org.youcode.itlens.owner.domain.Owner;
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
    public PagedResponse<OwnerResponseDTO> getAll(Pageable pageable) {
        Page<Owner> ownerPage = repository.findAll(pageable);
        List<OwnerResponseDTO> owners = ownerPage.getContent().stream().map(mapper::toDto).toList();
        return new PagedResponse<>(
                owners,
                ownerPage.getNumber(),
                ownerPage.getSize(),
                ownerPage.getTotalElements(),
                ownerPage.getTotalPages(),
                ownerPage.isLast()
        );
    }

    @Override
    public OwnerResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + id + " not found"));
    }

    @Override
    public OwnerResponseDTO create(OwnerRequestDTO ownerRequestDTO) {
        Owner owner = mapper.toEntity(ownerRequestDTO);
        return mapper.toDto((repository.save(owner)));
    }

    @Override
    public OwnerResponseDTO update(Long id, OwnerRequestDTO ownerRequestDTO) {
        Owner owner = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + id + " not found"));
        owner.setName(ownerRequestDTO.name());
        return mapper.toDto(repository.save(owner));
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException("Owner with id " + id + " not found");
        repository.deleteById(id);
    }
}
