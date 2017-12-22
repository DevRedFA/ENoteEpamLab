package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.NoteJpaEntity;
import com.epam.dao.jparepository.NoteJpaRepository;
import com.epam.mapper.NoteMapper;
import com.epam.service.interfaces.UserService;
import com.epam.service.models.Note;
import com.epam.service.models.NoteRepository;
import com.epam.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaProxyNoteRepository implements NoteRepository {

  @Autowired
  private NoteJpaRepository jpaRepository;

  @Autowired
  private UserService userService;

  @Override
  public Note save(Note note) {
    NoteJpaEntity savedEntity = jpaRepository.save(NoteMapper.toNoteJpaEntity(note));
    return NoteMapper.toNote(savedEntity);
  }

  @Override
  public void update(Note note) {
    NoteJpaEntity noteJpaEntity = NoteMapper.toNoteJpaEntity(note);
    jpaRepository.save(noteJpaEntity);
  }

  @Override
  public List<Note> all() {
    return NoteMapper.toNotes(jpaRepository.findAll());
  }

  @Override
  public List<Note> getByUserId(long userId) {
    return NoteMapper.toNotes(jpaRepository.findAllByUserId(userId));
  }

  @Override
  public Note getById(long id) {
    return NoteMapper.toNote(jpaRepository.getOne(id));
  }

  @Override
  public void delete(long noteId) {
    jpaRepository.delete(noteId);
  }
}