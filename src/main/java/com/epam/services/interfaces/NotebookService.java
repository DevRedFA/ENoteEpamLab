package com.epam.services.interfaces;

import com.epam.models.Notebook;

import java.util.List;

public interface NotebookService {

    Notebook save(Notebook notebook);

    List<Notebook> getByUserId(int id);

    List<Notebook> all();

    void update(Notebook notebook);
}
