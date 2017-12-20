package com.epam.dto.implementations;


import com.epam.dto.NoteDto;
import com.epam.mapper.NoteMapper;
import com.epam.service.models.Note;
import com.epam.dto.interfaces.NoteDtoService;
import com.epam.service.interfaces.NoteService;
import com.epam.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NoteDtoServiceImpl implements NoteDtoService {


    @Autowired
    TagService tagService;

    @Autowired
    private NoteService noteService;

    public NoteDto save(NoteDto noteDto) {
        Note note = noteService.save(NoteMapper.toNote(noteDto));
        return NoteMapper.toNoteDto(note);
    }

    @Override
    public NoteDto save(long noteDtobookId, NoteDto noteDto) {
        Note note = noteService.save(noteDtobookId, NoteMapper.toNote(noteDto));
        return NoteMapper.toNoteDto(note);
    }

    public List<NoteDto> all() {
        return NoteMapper.toNoteDtos(noteService.all());
    }

    public List<NoteDto> getByUserId(long userId) {
        return NoteMapper.toNoteDtos(noteService.getByUserId(userId));
    }

    public NoteDto getById(long id) {
        return NoteMapper.toNoteDto(noteService.getById(id));
    }

    public void update(NoteDto noteDto) {
        noteService.update(NoteMapper.toNote(noteDto));
    }

    @Override
    public void update(long noteDtoId, NoteDto noteDto) {
        noteService.update(noteDtoId, NoteMapper.toNote(noteDto));
    }

    @Override
    public void delete(long noteDtoId) {
        noteService.delete(noteDtoId);
    }

    @Override
    public List<NoteDto> getByUserIdAndTagId(long userId, long tagId) {
        return NoteMapper.toNoteDtos(noteService.getByUserIdAndTagId(userId, tagId));
    }

    @Override
    public List<NoteDto> getByNotebookId(long notebookId) {
        return NoteMapper.toNoteDtos(noteService.getByNotebookId(notebookId));
    }

    @Override
    public List<NoteDto> getByNotebookIdAndTagId(long notebookId, long tagId) {
        return NoteMapper.toNoteDtos(noteService.getByNotebookIdAndTagId(notebookId, tagId));
    }
}
