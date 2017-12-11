package com.epam.services.interfaces;

import com.epam.models.Tag;

import java.util.List;

public interface TagService {

    Tag save(Tag tag);

    List<Tag> all();

    Tag getById(int id);

    void update(Tag tag);
}
