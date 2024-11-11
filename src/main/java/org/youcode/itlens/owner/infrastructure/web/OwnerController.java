package org.youcode.itlens.owner.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.application.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService service;

    @GetMapping
    public ResponseEntity<List<OwnerResponseDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<OwnerResponseDTO> owners = service.getAll(pageable);
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> findById(@PathVariable Long id) {
        OwnerResponseDTO owner = service.getById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping
    public ResponseEntity<OwnerResponseDTO> create(@RequestBody @Valid OwnerRequestDTO dto) {
        OwnerResponseDTO newOwner = service.create(dto);
        return new ResponseEntity<>(newOwner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> update(@PathVariable Long id, @RequestBody @Valid OwnerRequestDTO dto) {
        OwnerResponseDTO updatedOwner = service.update(id, dto);
        return ResponseEntity.ok(updatedOwner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
