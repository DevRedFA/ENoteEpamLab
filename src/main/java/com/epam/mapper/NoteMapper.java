package com.epam.mapper;

import com.epam.dao.entity.NoteJpaEntity;
import com.epam.dto.NoteDto;
import com.epam.service.models.Note;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class NoteMapper {
    private NoteMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static Note toNote(NoteDto noteDto) {
        return modelMapper.map(noteDto, Note.class);
    }

    public static NoteDto toNoteDto(Note note) {
        return modelMapper.map(note, NoteDto.class);
    }

    public static Note toNote(NoteJpaEntity noteJpaEntity) {
        return modelMapper.map(noteJpaEntity, Note.class);
    }

    public static NoteJpaEntity toNoteJpaEntity(Note note) {
        return modelMapper.map(note, NoteJpaEntity.class);
    }

    public static List<NoteJpaEntity> toNoteJpaEntities(List<Note> notes) {
        List<NoteJpaEntity> list = new ArrayList<>();
        for (Note note : notes) {
            list.add(modelMapper.map(note, NoteJpaEntity.class));
        }
        return list;
    }

    public static List<Note> toNotes(List<NoteJpaEntity> notes) {
        List<Note> list = new ArrayList<>();
        for (NoteJpaEntity note : notes) {
            list.add(modelMapper.map(note, Note.class));
        }
        return list;
    }

    public static List<NoteDto> toNoteDtos(List<Note> notes) {
        List<NoteDto> list = new ArrayList<>();
        for (Note note : notes) {
            list.add(modelMapper.map(note, NoteDto.class));
        }
        return list;
    }
}
