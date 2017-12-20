package com.epam.service.interfaces;

import com.epam.service.models.Notebook;

import java.util.List;

public interface NotebookService {

    Notebook save(Notebook notebook);

    Notebook save(long userId, Notebook notebook);

    Notebook getById(long id);

    List<Notebook> all();

    List<Notebook> getByUserId(long id);

    List<Notebook> getByUserIdAndTagId(long userId, long tagId);

    void update(Notebook notebook);

    void update(long notebookId, Notebook notebook);

    void delete(Notebook notebook);

    void delete(long notebookId);


}
