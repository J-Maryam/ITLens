package org.youcode.itlens.owner.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.youcode.itlens.common.application.dto.PagedResponse;
import org.youcode.itlens.common.domain.exception.EntityNotFoundException;
import org.youcode.itlens.owner.application.dto.OwnerRequestDTO;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.application.mapper.OwnerMapper;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.owner.domain.OwnerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {
    @Mock
    private OwnerRepository repository;

    @Mock
    private OwnerMapper mapper;

    @InjectMocks
    private OwnerServiceImpl service;

    @Test
    void getAll_ShouldReturnPagedResponse() {
        Owner owner = new Owner();
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO(1L, "Test Owner", List.of());

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(owner)));
        when(mapper.toDto(owner)).thenReturn(ownerResponseDTO);

        PagedResponse<OwnerResponseDTO> result = service.getAll(Pageable.unpaged());

        assertEquals(1, result.content().size());
        assertEquals("Test Owner", result.content().get(0).name());
        verify(repository, times(1)).findAll(any(Pageable.class));
        verify(mapper, times(1)).toDto(owner);
    }

    @Test
    void getById_ShouldReturnOwnerResponseDTO_WhenOwnerExists() {
        Long id = 1L;
        Owner owner = new Owner();
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO(id, "Test Owner", List.of());
        when(repository.findById(id)).thenReturn(Optional.of(owner));
        when(mapper.toDto(owner)).thenReturn(ownerResponseDTO);

        OwnerResponseDTO result = service.getById(id);

        assertEquals(id, result.id());
        assertEquals("Test Owner", result.name());
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(owner);
    }

    @Test
    void getById_ShouldThrowEntityNotFoundException_WhenOwnerDoesNotExist() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.getById(id));
        assertEquals("Owner with id 1 not found", exception.getMessage());
        verify(repository, times(1)).findById(id);
        verify(mapper, never()).toDto(any());
    }

    @Test
    void create_ShouldReturnOwnerResponseDTO_WhenOwnerIsCreated() {
        OwnerRequestDTO requestDTO = new OwnerRequestDTO("Test Owner");
        Owner owner = new Owner();
        owner.setName("Test Owner");
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO(1L, "Test Owner", List.of());

        when(mapper.toEntity(requestDTO)).thenReturn(owner);
        when(repository.save(owner)).thenReturn(owner);
        when(mapper.toDto(owner)).thenReturn(ownerResponseDTO);

        OwnerResponseDTO result = service.create(requestDTO);

        assertEquals("Test Owner", result.name());
        verify(repository, times(1)).save(owner);
    }

    @Test
    void update_ShouldReturnUpdatedOwnerResponseDTO_WhenOwnerExists() {
        Long id = 1L;
        OwnerRequestDTO requestDTO = new OwnerRequestDTO("Updated Owner");
        Owner owner = new Owner();
        owner.setName("Original Owner");
        OwnerResponseDTO updatedResponseDTO = new OwnerResponseDTO(id, "Updated Owner", List.of());

        when(repository.findById(id)).thenReturn(Optional.of(owner));
        when(repository.save(owner)).thenReturn(owner);
        when(mapper.toDto(owner)).thenReturn(updatedResponseDTO);

        OwnerResponseDTO result = service.update(id, requestDTO);

        assertEquals("Updated Owner", result.name());
        verify(repository, times(1)).save(owner);
    }

    @Test
    void update_ShouldThrowEntityNotFoundException_WhenOwnerDoesNotExist() {
        Long id = 1L;
        OwnerRequestDTO requestDTO = new OwnerRequestDTO("Updated Owner");
        when(repository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.update(id, requestDTO));
        assertEquals("Owner with id 1 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}
