package com.epam.controller;

import com.epam.models.Note;
import com.epam.services.interfaces.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotesFromUser(@PathVariable long userId) {
        return noteService.getByUserId(userId);
    }

    @RequestMapping(value = "/{userId}/tag/{tagId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotesFromUserWithTag(@PathVariable long userId, @PathVariable long tagId) {
        return noteService.getByUserIdAndTagId(userId, tagId);
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotesFromNotebook(@PathVariable long notebookId) {
        return noteService.getByNotebookId(notebookId);
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Note createNoteToNotebook(@PathVariable long notebookId, @RequestParam("note") String noteDtoString) throws IOException {
        Note note = new ObjectMapper().readValue(noteDtoString, Note.class);
        return noteService.save(notebookId, note);
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable long noteId) {
        return noteService.getById(noteId);
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean updateNote(@PathVariable long noteId, @RequestParam("note") String noteDtoString) throws IOException {
        Note note = new ObjectMapper().readValue(noteDtoString, Note.class);
        noteService.update(noteId, note);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteNote(@PathVariable long noteId) {
        noteService.delete(noteId);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}/tag/{tagId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotesFromNotebookWithTag(@PathVariable long notebookId,
                                                     @PathVariable long tagId) {
        return noteService.getByNotebookIdAndTagId(notebookId, tagId);
    }
}
