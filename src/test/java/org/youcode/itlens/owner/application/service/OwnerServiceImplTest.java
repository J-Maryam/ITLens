package org.youcode.itlens.owner.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.youcode.itlens.owner.application.dto.OwnerResponseDTO;
import org.youcode.itlens.owner.application.mapper.OwnerMapper;
import org.youcode.itlens.owner.domain.Owner;
import org.youcode.itlens.owner.domain.OwnerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void getAll_ShouldReturnListOfOwnerResponseDTO() {
        Owner owner = new Owner();
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO(1L, "Test Owner", List.of());
        when(repository.findAll()).thenReturn(List.of(owner));
        when(mapper.toDto(owner)).thenReturn(ownerResponseDTO);

        List<OwnerResponseDTO> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Test Owner", result.get(0).name());
        verify(repository, times(1)).findAll();
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
}
