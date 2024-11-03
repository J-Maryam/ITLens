package org.youcode.itlens.common.application.service;

import java.util.List;

public interface GenericService <T, ID, RequestDto, ResponseDto>{
    List<T> getAll();
    T getById(ID id);
    T create(RequestDto requestDto);
    T update(ID id, RequestDto requestDto);
    void delete(ID id);
}
