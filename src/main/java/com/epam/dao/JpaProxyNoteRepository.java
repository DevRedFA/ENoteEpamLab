package com.epam.dao;

import com.epam.model.Note;
import com.epam.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaProxyNoteRepository implements NoteRepository {

    @Autowired
    private NoteJpaRepository jpaRepository;

    public Note save(Note note) {
        NoteJpaEntity newEntity = new NoteJpaEntity(note);
        NoteJpaEntity savedEntity = jpaRepository.save(newEntity);
        return savedEntity.toNote();
    }

    public void update(Note note) {
        NoteJpaEntity newEntity = new NoteJpaEntity(note);
        // is this really needed?
        jpaRepository.save(newEntity);
    }

    public List<Note> all() {
        List<NoteJpaEntity> entities = jpaRepository.findAll();
        List<Note> notes = new ArrayList<Note>(entities.size());
        for (NoteJpaEntity entity : entities) {
            Note note = entity.toNote();
            notes.add(note);
        }
        return notes;
    }

    public List<Note> getByUserId(int userId) {
        List<NoteJpaEntity> entities = jpaRepository.findAll();
        List<Note> notes = new ArrayList<Note>(entities.size());
        for (NoteJpaEntity entity : entities) {
            Note note = entity.toNote();
            if(note.getUser_id() == userId) {
                notes.add(note);
            }
        }
        return notes;
    }

    public Note getById(int id) {
        List<NoteJpaEntity> entities = jpaRepository.findAll();
        for (NoteJpaEntity entity : entities) {
            Note note = entity.toNote();
            if(note.getId() == id) {
                return note;
            }
        }
        return null;
    }
}