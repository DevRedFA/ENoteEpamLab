package com.epam.dao;

import com.epam.model.Notebook;
import com.epam.model.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaProxyNotebookRepository implements NotebookRepository {

    @Autowired
    private NotebookJpaRepository jpaRepository;

    public Notebook save(Notebook notebook) {
        NotebookJpaEntity newEntity = new NotebookJpaEntity(notebook);
        NotebookJpaEntity savedEntity = jpaRepository.save(newEntity);
        return savedEntity.toNotebook();
    }

    public void update(Notebook notebook) {
        NotebookJpaEntity newEntity = new NotebookJpaEntity(notebook);
        // is this really needed?
        jpaRepository.save(newEntity);
    }

    public List<Notebook> getByUserId(int userId) {
        List<NotebookJpaEntity> entities = jpaRepository.findAll();
        List<Notebook> notebooks = new ArrayList<Notebook>(entities.size());
        for (NotebookJpaEntity entity : entities) {
            Notebook notebook = entity.toNotebook();
            if(notebook.getUser_id() == userId) {
                notebooks.add(notebook);
            }
        }
        return notebooks;
    }

    public List<Notebook> all() {
        List<NotebookJpaEntity> entities = jpaRepository.findAll();
        List<Notebook> notebooks = new ArrayList<Notebook>(entities.size());
        for (NotebookJpaEntity entity : entities) {
            Notebook notebook = entity.toNotebook();
            notebooks.add(notebook);
        }
        return notebooks;
    }
}