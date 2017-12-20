package com.epam.dto.interfaces;

import com.epam.dto.TagDto;

import java.util.List;

public interface TagDtoService {

    TagDto save(TagDto tagDto);

    TagDto save(long userId, TagDto tagDto);

    List<TagDto> all();

    List<TagDto> getByUserId(long id);

    List<TagDto> getByNotebookId(long notebookId);

    List<TagDto> getByNoteId(long notebookId);

    TagDto getById(long id);

    void update(TagDto tagDto);

    void update(long id, TagDto tagDto);

    void delete(long tagDtoId);
}
