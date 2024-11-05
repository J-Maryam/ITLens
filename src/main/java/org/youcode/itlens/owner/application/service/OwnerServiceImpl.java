package org.youcode.itlens.owner.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.mapper.OwnerMapper;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.owner.domain.OwnerRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public List<Owner> getAll() {
        return List.of();
    }

    @Override
    public Owner getById(Long aLong) {
        return null;
    }

    @Override
    public Owner create(OwnerRequestDTO ownerRequestDTO) {
        return null;
    }

    @Override
    public Owner update(Long aLong, OwnerRequestDTO ownerRequestDTO) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
