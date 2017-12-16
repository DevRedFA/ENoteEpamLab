package com.epam.controller;

import com.epam.models.Notebook;
import com.epam.models.User;
import com.epam.services.interfaces.NotebookService;
import com.epam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/notebooks")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getAllUserNotebooks(@PathVariable long userId, ModelMap model) {
        List<Notebook> notebooks = notebookService.getByUserId(userId);
        // TODO: upload notebooks instance to model
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    public String getNotebook(@PathVariable long userId, @PathVariable long notebookId,
                              ModelMap model) {
        List<Notebook> notebooks = notebookService.getByUserId(userId);
        Notebook notebook = notebooks.get((int) notebookId);
        // TODO: upload notebook instance to model
        return "user";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String createNotebook(@PathVariable long userId, ModelMap model) {
        Notebook notebook = Notebook.builder()
                .id((int) userId)
                // TODO: add all other fields
                .build();
        notebookService.save(notebook);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.POST)
    public String updateNotebook(@PathVariable long userId, @PathVariable long notebookId,
                                 ModelMap model) {
        List<Notebook> notebooks = notebookService.getByUserId((int) userId);
        Notebook notebook = notebooks.get((int) notebookId);
        //TODO: update notebook instance fields.
        notebookService.update(notebook);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.DELETE)
    public String deleteNotebook(@PathVariable long userId, @PathVariable long notebookId,
                                 ModelMap model) {
        List<Notebook> notebooks = notebookService.getByUserId(userId);
        Notebook notebook = notebooks.get((int) notebookId);
        notebookService.delete(notebook);
        return "user";
    }
}
