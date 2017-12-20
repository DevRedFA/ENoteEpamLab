package com.epam.dto.interfaces;


import com.epam.dto.NoteDto;

import java.util.List;

public interface NoteDtoService {

    NoteDto save(NoteDto noteDto);

    NoteDto save(long notebookId, NoteDto noteDto);

    List<NoteDto> all();

    List<NoteDto> getByUserId(long userId);

    List<NoteDto> getByUserIdAndTagId(long userId, long tagId);

    List<NoteDto> getByNotebookId(long notebookId);

    List<NoteDto> getByNotebookIdAndTagId(long notebookId, long tagId);

    NoteDto getById(long id);

    void update(NoteDto noteDto);

    void update(long noteDtoId, NoteDto noteDto);

    void delete(long noteDtoId);


}
