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

    public Notebook save(Notebook newNotebook) {
        NotebookJpaEntity newEntity = new NotebookJpaEntity(newNotebook);
        NotebookJpaEntity savedEntity = jpaRepository.save(newEntity);
        return savedEntity.toNotebook();
    }

    public void update(Notebook notebook) {
        NotebookJpaEntity newEntity = new NotebookJpaEntity(notebook);
        jpaRepository.save(newEntity);
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