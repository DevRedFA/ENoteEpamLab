package com.epam.services.implementations;

import com.epam.dao.JpaProxyNotebookRepository;
import com.epam.model.Notebook;
import com.epam.services.interfaces.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    JpaProxyNotebookRepository jpaProxyNotebookRepository;

    public Notebook save(Notebook notebook) {
        return jpaProxyNotebookRepository.save(notebook);
    }

    public List<Notebook> getByUserId(int id) {
        return jpaProxyNotebookRepository.getByUserId(id);
    }

    public List<Notebook> all() {
        return jpaProxyNotebookRepository.all();
    }

    public void update(Notebook notebook) {
        jpaProxyNotebookRepository.update(notebook);
    }
}
