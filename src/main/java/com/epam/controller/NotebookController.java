package com.epam.controller;

import com.epam.models.Notebook;
import com.epam.services.interfaces.NotebookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/notebooks")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<Notebook> getAllNotebooksFromUser(@PathVariable long userId) {
        return notebookService.getByUserId(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String createNotebook(@PathVariable long userId, @RequestParam("notebook") String notebookDtoString) throws IOException {
        Notebook notebook = new ObjectMapper().readValue(notebookDtoString, Notebook.class);
        notebookService.save(userId, notebook);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    public Notebook getNotebook(@PathVariable long notebookId) {
        return notebookService.getById(notebookId);
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.POST)
    public boolean updateNotebook(@PathVariable long notebookId, @RequestParam("notebook") String notebookDtoString) throws IOException {
        Notebook notebook = new ObjectMapper().readValue(notebookDtoString, Notebook.class);
        notebookService.update(notebookId, notebook);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.DELETE)
    public boolean deleteNotebook(@PathVariable long notebookId) {
        notebookService.delete(notebookId);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}/tag/{tagId}", method = RequestMethod.GET)
    public List<Notebook> getAllNotebooksFromUserWithTag(@PathVariable long userId, @PathVariable long tagId) {
        return notebookService.getByUserIdAndTagId(userId, tagId);
    }

}
