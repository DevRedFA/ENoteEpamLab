package com.epam.mapper;

import com.epam.dao.entity.NoteJpaEntity;
import com.epam.models.Note;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface NoteMapper {

    Note noteEntityToNote(NoteJpaEntity note);

    List<Note> noteEntitiesToNotes(List<NoteJpaEntity> notes);

    NoteJpaEntity noteToNoteEntity(Note note);

    List<NoteJpaEntity> notesToNoteEntities(List<Note> notes);
}