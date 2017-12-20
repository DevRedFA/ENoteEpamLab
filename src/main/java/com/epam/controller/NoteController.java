package com.epam.controller;

import com.epam.dto.NoteDto;
import com.epam.dto.interfaces.NoteDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/noteDtos")
public class NoteController {

    @Autowired
    private NoteDtoService noteDtoService;

    @GetMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDto> getAllNoteDtosFromUser(@PathVariable long userId) {
        return noteDtoService.getByUserId(userId);
    }

    @GetMapping(value = "/{userId}/tag/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDto> getAllNoteDtosFromUserWithTag(@PathVariable long userId, @PathVariable long tagId) {
        return noteDtoService.getByUserIdAndTagId(userId, tagId);
    }

    @GetMapping(value = "/{userId}/{noteDtobookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDto> getAllNoteDtosFromNoteDtobook(@PathVariable long notebookId) {
        return noteDtoService.getByNotebookId(notebookId);
    }

    @PutMapping(value = "/{userId}/{noteDtobookId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto createNoteDtoToNoteDtobook(@PathVariable long noteDtobookId, @RequestBody NoteDto noteDto) {
        return noteDtoService.save(noteDtobookId, noteDto);
    }

    @GetMapping(value = "/{userId}/{noteDtobookId}/{noteDtoId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto getNoteDto(@PathVariable long noteDtoId) {
        return noteDtoService.getById(noteDtoId);
    }

    @PostMapping(value = "/{userId}/{noteDtobookId}/{noteDtoId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateNoteDto(@PathVariable long noteDtoId, @RequestBody NoteDto noteDto) {
        noteDtoService.update(noteDtoId, noteDto);
        return true;
    }

    @DeleteMapping(value = "/{userId}/{noteDtobookId}/{noteDtoId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteNoteDto(@PathVariable long noteDtoId) {
        noteDtoService.delete(noteDtoId);
        return true;
    }

    @GetMapping(value = "/{userId}/{noteDtobookId}/tag/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDto> getAllNoteDtosFromNoteDtobookWithTag(@PathVariable long noteDtobookId,
                                                              @PathVariable long tagId) {
        return noteDtoService.getByNotebookIdAndTagId(noteDtobookId, tagId);
    }
}
