package com.epam.model;

import java.util.List;

public interface TagRepository {

    Tag save(Tag tag);

    void update(Tag tag);

    List<Tag> all();
}
