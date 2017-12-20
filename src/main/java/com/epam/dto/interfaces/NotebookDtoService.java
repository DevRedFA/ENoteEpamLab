package com.epam.dto.interfaces;

import com.epam.dto.NotebookDto;

import java.util.List;

public interface NotebookDtoService {

    NotebookDto save(NotebookDto notebookDto);

    NotebookDto save(long userId, NotebookDto notebookDto);

    NotebookDto getById(long id);

    List<NotebookDto> all();

    List<NotebookDto> getByUserId(long id);

    List<NotebookDto> getByUserIdAndTagId(long userId, long tagId);

    void update(NotebookDto notebookDto);

    void update(long notebookDtoId, NotebookDto notebookDto);

    void delete(NotebookDto notebookDto);

    void delete(long notebookDtoId);


}
