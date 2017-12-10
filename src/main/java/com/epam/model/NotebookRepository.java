package com.epam.model;

import java.util.List;

public interface NotebookRepository {

    Notebook save(Notebook notebook);

    void update(Notebook notebook);

    List<Notebook> all();

    List<Notebook> getByUserId(int userId);
}
