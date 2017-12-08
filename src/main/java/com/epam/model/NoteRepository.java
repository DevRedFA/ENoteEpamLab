package com.epam.model;

import java.util.List;

public interface NoteRepository {

    Note save(Note note);

    void update(Note note);

    List<Note> all();
}
