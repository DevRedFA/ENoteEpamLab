package com.epam.controller;

import com.epam.services.interfaces.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/notebooks")
public class NotebookController {

  @Autowired
  private NotebookService notebookService;

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public String getAllUserNotebooks(@PathVariable long userId, ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
  public String getNotebook(@PathVariable long userId, @PathVariable long notebookId,
                            ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
  public String createNotebook(@PathVariable long userId, ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.POST)
  public String updateNotebook(@PathVariable long userId, @PathVariable long notebookId,
                               ModelMap model) {
    return "user";
  }

  @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.DELETE)
  public String deleteNotebook(@PathVariable long userId, @PathVariable long notebookId,
                               ModelMap model) {
    return "user";
  }
}
