package com.epam.services.implementations;

import com.epam.dao.JpaProxyNoteRepository;
import com.epam.model.Note;
import com.epam.services.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    JpaProxyNoteRepository jpaProxyNoteRepository;

    public Note save(Note note) {
        return jpaProxyNoteRepository.save(note);
    }

    public List<Note> all() {
        return jpaProxyNoteRepository.all();
    }

    public List<Note> getByUserId(int userId) {
        return jpaProxyNoteRepository.getByUserId(userId);
    }

    public Note getById(int id) {
        return jpaProxyNoteRepository.getById(id);
    }

    public void update(Note note) {
        jpaProxyNoteRepository.update(note);
    }
}
