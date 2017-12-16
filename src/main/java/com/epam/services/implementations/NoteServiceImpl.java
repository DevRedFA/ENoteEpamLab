package com.epam.services.implementations;

import com.epam.dao.jpaproxyrepository.JpaProxyNoteRepository;
import com.epam.models.Note;
import com.epam.services.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NoteServiceImpl implements NoteService {

    @Autowired
    JpaProxyNoteRepository jpaProxyNoteRepository;

    public Note save(Note note) {
        return jpaProxyNoteRepository.save(note);
    }

    public List<Note> all() {
        return jpaProxyNoteRepository.all();
    }

    public List<Note> getByUserId(long userId) {
        return jpaProxyNoteRepository.getByUserId(userId);
    }

    public Note getById(long id) {
        return jpaProxyNoteRepository.getById(id);
    }

    public void update(Note note) {
        jpaProxyNoteRepository.update(note);
    }

    @Override
    public void delete(long noteId) {
        jpaProxyNoteRepository.delete(noteId);
    }
}
