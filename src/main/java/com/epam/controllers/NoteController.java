package com.epam.controllers;

import com.epam.services.interfaces.NoteService;
import com.epam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/notes")
public class NoteController {

  @Autowired
  private NoteService noteService;

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
  public String getAllNotesFromNotebook(@PathVariable long userId, @PathVariable long notebookId,
                                        ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.PUT)
  public String createNote(@PathVariable long userId, @PathVariable long notebookId,
                           ModelMap model) {
    //   noteService.save(note);
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
  public String getNote(@PathVariable long userId, @PathVariable long notebookId,
                        @PathVariable long noteId, ModelMap model) {
    //    userService.update(user);
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.POST)
  public String updateNote(@PathVariable long userId, @PathVariable long notebookId,
                           @PathVariable long noteId, ModelMap model) {
    //    userService.update(user);
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.DELETE)
  public String deleteNote(@PathVariable long userId, @PathVariable long notebookId,
                           @PathVariable long noteId, ModelMap model) {
    //    userService.delete(user);
    return "user";
  }

}
