package com.epam.controller;

import com.epam.models.Note;
import com.epam.models.Notebook;
import com.epam.models.Tag;
import com.epam.services.interfaces.NoteService;
import com.epam.services.interfaces.NotebookService;
import com.epam.services.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    public String getAllNotesFromNotebook(@PathVariable long userId, @PathVariable long notebookId,
                                          ModelMap model) {
        Notebook notebook = notebookService.getById(notebookId);
        Set<Note> notes = notebook.getNotes();
        //TODO: upload notes instance to model
        //TODO: refactor noteService method for removing notebookService from that controller
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/tag/{tagId}", method = RequestMethod.GET)
    public String getAllNotesWithTagFromNotebook(@PathVariable long userId,
                                                 @PathVariable long notebookId,
                                                 @PathVariable long tagId, ModelMap model) {
        List<Note> result = new ArrayList<>();
        Notebook notebook = notebookService.getById(notebookId);
        Set<Note> notes = notebook.getNotes();
        Tag tagToFilter = tagService.getById(tagId);
        for (Note note : notes) {
            if (note.getTags().contains(tagToFilter)) {
                result.add(note);
            }
        }
        //TODO: upload notes instance to model
        //TODO: refactor noteService method for removing notebookService and tagService from that controller
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.PUT)
    public String createNoteToNotebook(@PathVariable long userId, @PathVariable long notebookId,
                                       ModelMap model) {
        Note note = new Note();
        //TODO: set parameter from model to note instance
        Notebook notebook = notebookService.getById(notebookId);
        notebook.getNotes().add(note);
        notebookService.update(notebook);
        return "user";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String createNote(@PathVariable long userId, @PathVariable long notebookId,
                             ModelMap model) {
        Note note = new Note();
        //TODO: set parameter from model to note instance
        noteService.save(note);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
    public String getNote(@PathVariable long userId, @PathVariable long notebookId,
                          @PathVariable long noteId, ModelMap model) {
        Note note = noteService.getById(noteId);
        //TODO: return to model note instance
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.POST)
    public String updateNote(@PathVariable long userId, @PathVariable long notebookId,
                             @PathVariable long noteId, ModelMap model) {
        Note note = noteService.getById((int) noteId);
        //TODO: add changes to note
        noteService.update(note);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.DELETE)
    public String deleteNote(@PathVariable long userId, @PathVariable long notebookId,
                             @PathVariable long noteId, ModelMap model) {
        noteService.delete(noteId);
        return "user";
    }

}
