package com.epam.models;

import java.util.List;

public interface TagRepository {

    Tag save(Tag tag);

    void update(Tag tag);

    List<Tag> all();

    Tag getById(long id);

    void delete(long tagId);
}
