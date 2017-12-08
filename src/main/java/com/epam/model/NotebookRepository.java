package com.epam.model;

import java.util.List;

public interface NotebookRepository {

    Notebook save(Notebook notebook);

    void update(Notebook notebook);

    Notebook get(int userId);

    List<Notebook> all();
}
