package com.epam.models;

import java.util.List;

public interface NoteRepository {

    Note save(Note note);

    void update(Note note);

    List<Note> all();

    List<Note> getByUserId(long userId);

    Note getById(long id);

    void delete(long noteId);
}
