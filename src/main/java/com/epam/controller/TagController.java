package com.epam.controller;


import com.epam.models.Note;
import com.epam.models.Notebook;
import com.epam.models.Tag;
import com.epam.services.interfaces.NoteService;
import com.epam.services.interfaces.NotebookService;
import com.epam.services.interfaces.TagService;
import com.epam.services.interfaces.UserService;
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
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getAllUserTags(@PathVariable long userId,
                                 ModelMap model) {
        Set<Tag> tags = userService.getById(userId).getTags();
        //TODO: return to form tags instance
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{tagId}", method = RequestMethod.GET)
    public String getAllNotebookTags(@PathVariable long userId, @PathVariable long notebookId,
                                     @PathVariable long tagId, ModelMap model) {
        List<Notebook> result = new ArrayList<>();
        Tag tagToFilter = tagService.getById(tagId);
        List<Notebook> notebooks = notebookService.getByUserId(userId);
        for (Notebook notebook : notebooks) {
            for (Note note : notebook.getNotes()) {
                if (note.getTags().contains(tagToFilter)) {
                    result.add(notebook);
                    break;
                }
            }
        }
        //TODO: return to form result instance
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
    public String getAllNoteTags(@PathVariable long userId, @PathVariable long notebookId,
                                 @PathVariable long noteId, ModelMap model) {
        Note note = noteService.getById(noteId);
        Set<Tag> tags = note.getTags();
        //TODO: return tags to model
        return "user";
    }

    @RequestMapping(value = "/{userId}/{tagId}", method = RequestMethod.POST)
    public String updateTag(@PathVariable long userId, @PathVariable long tagId, ModelMap model) {
        Tag tag = tagService.getById(tagId);
        //TODO: change tag paramets
        tagService.update(tag);
        return "user";
    }

    @RequestMapping(value = "/{userId}/{tagId}", method = RequestMethod.DELETE)
    public String deleteTag(@PathVariable long userId, @PathVariable long tagId, ModelMap model) {
        tagService.delete(tagId);
        return "user";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String createTag(@PathVariable long userId, ModelMap model) {
        Tag tag = new Tag();
        //TODO: set parameters from model;
        tagService.save(tag);
        return "user";
    }


}
