package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.NoteJpaEntity;
import com.epam.dao.jparepository.NoteJpaRepository;
import com.epam.dao.mapper.NoteMapper;
import com.epam.models.Note;
import com.epam.models.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaProxyNoteRepository implements NoteRepository {

    @Autowired
    private NoteJpaRepository jpaRepository;

    @Autowired
    private NoteMapper noteMapper;

    public Note save(Note note) {
        NoteJpaEntity savedEntity = jpaRepository.save(noteMapper.noteToNoteEntity(note));
        return noteMapper.noteEntityToNote(savedEntity);
    }

    public void update(Note note) {
        jpaRepository.save(noteMapper.noteToNoteEntity(note));
    }

    public List<Note> all() {
        return noteMapper.noteEntitiesToNotes(jpaRepository.findAll());
    }

    public List<Note> getByUserId(int userId) {
        List<NoteJpaEntity> entities = jpaRepository.findAll();
        List<Note> notes = new ArrayList<Note>(entities.size());
        for (NoteJpaEntity entity : entities) {
            if (entity.getUser().getId() == userId) {
                notes.add(noteMapper.noteEntityToNote(entity));
            }
        }
        return notes;
    }

    public Note getById(int id) {
        List<NoteJpaEntity> entities = jpaRepository.findAll();
        for (NoteJpaEntity entity : entities) {
            if (entity.getId() == id) {
                return noteMapper.noteEntityToNote(entity);
            }
        }
        return null;
    }
}