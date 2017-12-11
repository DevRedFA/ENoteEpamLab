package com.epam.models;

import java.util.List;

public interface TagRepository {

    Tag save(Tag tag);

    void update(Tag tag);

    List<Tag> all();
}
