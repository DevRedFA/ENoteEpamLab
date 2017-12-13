package com.epam.controllers;


import com.epam.services.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

  @Autowired
  private NoteService noteService;

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public String getAllUserTags(@PathVariable long userId,
                               ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
  public String getAllNotebookTags(@PathVariable long userId, @PathVariable long notebookId,
                                   ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
  public String getAllNoteTags(@PathVariable long userId, @PathVariable long notebookId,
                               @PathVariable long noteId, ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{tagId}", method = RequestMethod.POST)
  public String updateTag(@PathVariable long userId, @PathVariable long tagId, ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{tagId}", method = RequestMethod.DELETE)
  public String deleteTag(@PathVariable long userId, @PathVariable long tagId, ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
  public String createTag(@PathVariable long userId, ModelMap model) {
    return "user";
  }


}
