package org.youcode.itlens.common.application.service;

import java.util.List;

public interface GenericService <T, ID, RequestDto, ResponseDto>{
    List<ResponseDto> getAll();
    ResponseDto getById(ID id);
    ResponseDto create(RequestDto requestDto);
    ResponseDto update(ID id, RequestDto requestDto);
    void delete(ID id);
}
