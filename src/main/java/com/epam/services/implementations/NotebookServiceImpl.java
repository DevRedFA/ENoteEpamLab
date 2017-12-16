package com.epam.services.implementations;

import com.epam.dao.jpaproxyrepository.JpaProxyNotebookRepository;
import com.epam.models.Notebook;
import com.epam.services.interfaces.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    JpaProxyNotebookRepository jpaProxyNotebookRepository;

    public Notebook save(Notebook notebook) {
        return jpaProxyNotebookRepository.save(notebook);
    }

    public List<Notebook> getByUserId(long id) {
        return jpaProxyNotebookRepository.getByUserId(id);
    }

    @Override
    public Notebook getById(long id) {
        return jpaProxyNotebookRepository.getById(id);
    }

    public List<Notebook> all() {
        return jpaProxyNotebookRepository.all();
    }

    public void update(Notebook notebook) {
        jpaProxyNotebookRepository.update(notebook);
    }

    @Override
    public void delete(Notebook notebook) {
        jpaProxyNotebookRepository.delete(notebook);
    }
}
