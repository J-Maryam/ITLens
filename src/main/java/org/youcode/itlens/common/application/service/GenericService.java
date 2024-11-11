package org.youcode.itlens.common.application.service;

import org.springframework.data.domain.Pageable;
import org.youcode.itlens.common.application.dto.PagedResponse;

import java.util.List;

public interface GenericService <T, ID, RequestDto, ResponseDto>{
    PagedResponse<ResponseDto> getAll(Pageable pageable);
    ResponseDto getById(ID id);
    ResponseDto create(RequestDto requestDto);
    ResponseDto update(ID id, RequestDto requestDto);
    void delete(ID id);
}
