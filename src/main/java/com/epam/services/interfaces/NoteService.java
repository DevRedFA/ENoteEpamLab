package com.epam.services.interfaces;

import com.epam.models.Note;

import java.util.List;

public interface NoteService {

    Note save(Note note);

    Note save(long notebookId, Note note);

    List<Note> all();

    List<Note> getByUserId(long userId);

    List<Note> getByUserIdAndTagId(long userId, long tagId);

    List<Note> getByNotebookId(long notebookId);

    List<Note> getByNotebookIdAndTagId(long notebookId, long tagId);
    
    Note getById(long id);

    void update(Note note);

    void update(long noteId, Note note);

    void delete(long noteId);


}
