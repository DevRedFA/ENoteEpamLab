package com.epam.controller;


import com.epam.models.Note;
import com.epam.models.Notebook;
import com.epam.models.Tag;
import com.epam.models.User;
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
    public String getAllTagsFromUser(@PathVariable long userId,
                                     ModelMap model) {
        Set<Tag> tags = userService.getById(userId).getTags();
        //TODO: return to form tags instance
        return "user";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String createTag(@PathVariable long userId, ModelMap model) {
        Tag tag = new Tag();
        User user = userService.getById(userId);
        user.getTags().add(tag);
        userService.update(user);
        //TODO: set parameters from model;
        tagService.save(tag);
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

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    public String getAllTagsFromNotebook(@PathVariable long userId, @PathVariable long notebookId,
                                         ModelMap model) {
        List<Tag> tags = new ArrayList<>();
        Notebook notebook = notebookService.getById(notebookId);
        for (Note note : notebook.getNotes()) {
            tags.addAll(note.getTags());
        }
        //TODO: return to form tags instance
        return "user";
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
    public String getAllTagsFromNote(@PathVariable long userId, @PathVariable long notebookId,
                                     @PathVariable long noteId, ModelMap model) {
        Note note = noteService.getById(noteId);
        Set<Tag> tags = note.getTags();
        //TODO: return tags to model
        return "user";
    }
}
