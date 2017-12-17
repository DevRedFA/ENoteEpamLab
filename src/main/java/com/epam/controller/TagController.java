package com.epam.controller;

import com.epam.models.Tag;
import com.epam.services.interfaces.TagService;
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
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<Tag> getAllTagsFromUser(@PathVariable long userId) {
        return tagService.getByUserId(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Tag createTag(@PathVariable long userId, @RequestParam("tag") String tagDtoString) throws IOException {
        Tag tag = new ObjectMapper().readValue(tagDtoString,
                Tag.class);
        tagService.save(userId, tag);
        return tag;
    }

    @RequestMapping(value = "/{userId}/{tagId}", method = RequestMethod.POST)
    public boolean updateTag(@PathVariable long tagId, @RequestParam("tag") String tagDtoString) throws IOException {
        Tag tag = new ObjectMapper().readValue(tagDtoString,
                Tag.class);
        tagService.update(tagId, tag);
        return true;
    }

    @RequestMapping(value = "/{userId}/{tagId}", method = RequestMethod.DELETE)
    public boolean deleteTag(@PathVariable long tagId) {
        tagService.delete(tagId);
        return true;
    }

    @RequestMapping(value = "/{userId}/{notebookId}", method = RequestMethod.GET)
    public List<Tag> getAllTagsFromNotebook(@PathVariable long notebookId) {
        return tagService.getByNotebookId(notebookId);
    }

    @RequestMapping(value = "/{userId}/{notebookId}/{noteId}", method = RequestMethod.GET)
    public List<Tag> getAllTagsFromNote(@PathVariable long noteId) {
        return tagService.getByNoteId(noteId);
    }
}
