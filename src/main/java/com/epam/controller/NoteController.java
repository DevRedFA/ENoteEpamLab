package com.epam.controller;

import com.epam.dto.NoteDto;
import com.epam.dto.interfaces.NoteDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notes")
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
  public List<NoteDto> getAllNotesFromUserWithTag(@PathVariable long userId,
                                                  @PathVariable long tagId) {
    return noteDtoService.getByUserIdAndTagId(userId, tagId);
  }

  @GetMapping(value = "/{userId}/{notebookId}")
  @ResponseStatus(HttpStatus.OK)
  public List<NoteDto> getAllNotesFromNotebook(@PathVariable long notebookId) {
    return noteDtoService.getByNotebookId(notebookId);
  }

  @PutMapping(value = "/{userId}/{notebookId}")
  @ResponseStatus(HttpStatus.OK)
  public NoteDto createNoteToNotebook(@PathVariable long notebookId, @RequestBody NoteDto noteDto) {
    return noteDtoService.save(notebookId, noteDto);
  }

  @GetMapping(value = "/{userId}/{notebookId}/{noteId}")
  @ResponseStatus(HttpStatus.OK)
  public NoteDto getNote(@PathVariable long noteId) {
    return noteDtoService.getById(noteId);
  }

  @PostMapping(value = "/{userId}/{notebookId}/{noteId}")
  @ResponseStatus(HttpStatus.OK)
  public boolean updateNoteDto(@PathVariable long noteId, @RequestBody NoteDto noteDto) {
    noteDtoService.update(noteId, noteDto);
    return true;
  }

  @DeleteMapping(value = "/{userId}/{notebookId}/{noteId}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteNoteDto(@PathVariable long noteId) {
    noteDtoService.delete(noteId);
    return true;
  }

  @GetMapping(value = "/{userId}/{notebookId}/tag/{tagId}")
  @ResponseStatus(HttpStatus.OK)
  public List<NoteDto> getAllNotesFromNotebookWithTag(@PathVariable long notebookId,
                                                      @PathVariable long tagId) {
    return noteDtoService.getByNotebookIdAndTagId(notebookId, tagId);
  }
}
