package com.epam.controller;

import com.epam.dto.TagDto;
import com.epam.dto.interfaces.TagDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

  @Autowired
  private TagDtoService tagDtoService;

  @GetMapping(value = "/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public List<TagDto> getAllTagsFromUser(@PathVariable long userId) {
    return tagDtoService.getByUserId(userId);
  }

  @PutMapping(value = "/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public TagDto createTag(@PathVariable long userId, @RequestBody TagDto tagDto) {
    return tagDtoService.save(userId, tagDto);
  }

  @PostMapping(value = "/{userId}/{tagId}")
  @ResponseStatus(HttpStatus.OK)
  public boolean updateTag(@PathVariable long tagId, @RequestBody TagDto tagDto) {
    tagDtoService.update(tagId, tagDto);
    return true;
  }

  @DeleteMapping(value = "/{userId}/{tagId}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteTag(@PathVariable long tagId) {
    tagDtoService.delete(tagId);
    return true;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{userId}/{notebookId}")
  public List<TagDto> getAllTagsFromNotebook(@PathVariable long notebookId) {
    return tagDtoService.getByNotebookId(notebookId);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{userId}/{notebookId}/{noteId}")
  public List<TagDto> getAllTagsFromNote(@PathVariable long noteId) {
    return tagDtoService.getByNoteId(noteId);
  }
}
