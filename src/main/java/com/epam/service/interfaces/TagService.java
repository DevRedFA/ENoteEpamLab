package com.epam.service.interfaces;

import com.epam.service.models.Tag;

import java.util.List;

public interface TagService {

    Tag save(Tag tag);

    Tag save(long userId, Tag tag);

    List<Tag> all();

    List<Tag> getByUserId(long id);

    List<Tag> getByNotebookId(long notebookId);

    List<Tag> getByNoteId(long notebookId);

    Tag getById(long id);

    void update(Tag tag);

    void update(long id, Tag tag);

    void delete(long tagId);
}
