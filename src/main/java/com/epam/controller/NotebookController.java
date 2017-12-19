package com.epam.controller;

import com.epam.models.Notebook;
import com.epam.services.interfaces.NotebookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/notebooks")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Notebook> getAllNotebooksFromUser(@PathVariable long userId) {
        return notebookService.getByUserId(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String createNotebook(@PathVariable long userId, @RequestParam("notebook") String notebookDtoString) throws IOException {
        Notebook notebook = new ObjectMapper().readValue(notebookDtoString, Notebook.class);
        notebookService.save(userId, notebook);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Notebook getNotebook(@PathVariable long notebookId) {
        return notebookService.getById(notebookId);
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean updateNotebook(@PathVariable long notebookId, @RequestParam("notebook") String notebookDtoString) throws IOException {
        Notebook notebook = new ObjectMapper().readValue(notebookDtoString, Notebook.class);
        notebookService.update(notebookId, notebook);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteNotebook(@PathVariable long notebookId) {
        notebookService.delete(notebookId);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}/tag/{tagId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Notebook> getAllNotebooksFromUserWithTag(@PathVariable long userId, @PathVariable long tagId) {
        return notebookService.getByUserIdAndTagId(userId, tagId);
    }

}
