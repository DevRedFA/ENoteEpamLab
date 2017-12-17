package com.epam.models;


import java.util.List;

public interface NotebookRepository {

    Notebook save(Notebook notebook);

    void update(Notebook notebook);

    List<Notebook> all();

    List<Notebook> findByUserId(long userId);

    void delete(Notebook notebook);

    Notebook getById(long id);
}
