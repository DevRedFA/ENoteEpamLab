package com.epam.controller;

import com.epam.dto.NotebookDto;
import com.epam.dto.interfaces.NotebookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notebooks")
public class NotebookController {

  @Autowired
  private NotebookDtoService notebookDtoService;

  @GetMapping(value = "/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public List<NotebookDto> getAllNotebooksFromUser(@PathVariable long userId) {
    return notebookDtoService.getByUserId(userId);
  }

  @PutMapping(value = "/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public NotebookDto createNotebook(@PathVariable long userId,
                                    @RequestBody NotebookDto notebookDto) {
    return notebookDtoService.save(userId, notebookDto);
  }

  @GetMapping(value = "/{userId}/{notebookId}")
  @ResponseStatus(HttpStatus.OK)
  public NotebookDto getNotebook(@PathVariable long notebookId) {
    return notebookDtoService.getById(notebookId);
  }

  @PostMapping(value = "/{userId}/{notebookId}")
  @ResponseStatus(HttpStatus.OK)
  public boolean updateNotebook(@PathVariable long notebookId,
                                @RequestBody NotebookDto notebookDto) {
    notebookDtoService.update(notebookId, notebookDto);
    return true;
  }

  @DeleteMapping(value = "/{userId}/{notebookId}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteNotebook(@PathVariable long notebookId) {
    notebookDtoService.delete(notebookId);
    return true;
  }

  @GetMapping(value = "/{userId}/{notebookId}/tag/{tagId}")
  @ResponseStatus(HttpStatus.OK)
  public List<NotebookDto> getAllNotebooksFromUserWithTag(@PathVariable long userId,
                                                          @PathVariable long tagId) {
    return notebookDtoService.getByUserIdAndTagId(userId, tagId);
  }

}
