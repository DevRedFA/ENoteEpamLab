package com.epam.services.interfaces;

import com.epam.models.Note;

import java.util.List;

public interface NoteService {

    Note save(Note note);

    List<Note> all();

    List<Note> getByUserId(int userId);

    Note getById(int id);

    void update(Note note);
}
