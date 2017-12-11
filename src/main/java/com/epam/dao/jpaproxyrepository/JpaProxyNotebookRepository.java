package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.dao.jparepository.NotebookJpaRepository;
import com.epam.dao.mapper.NotebookMapper;
import com.epam.models.Notebook;
import com.epam.models.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaProxyNotebookRepository implements NotebookRepository {

    @Autowired
    private NotebookJpaRepository jpaRepository;

    @Autowired
    private NotebookMapper notebookMapper;

    public Notebook save(Notebook notebook) {
        NotebookJpaEntity savedEntity = jpaRepository.save(notebookMapper.notebookToNotebookEntity(notebook));
        return notebookMapper.notebookEntityToNotebook(savedEntity);
    }

    public void update(Notebook notebook) {
        jpaRepository.save(notebookMapper.notebookToNotebookEntity(notebook));
    }

    public List<Notebook> all() {
        return notebookMapper.notebookEntitiesToNotebooks(jpaRepository.findAll());
    }

    public List<Notebook> getByUserId(int userId) {
        List<NotebookJpaEntity> entities = jpaRepository.findAll();
        List<Notebook> notebooks = new ArrayList<Notebook>(entities.size());
        for (NotebookJpaEntity entity : entities) {
            if (entity.getUser().getId() == userId) {
                notebooks.add(notebookMapper.notebookEntityToNotebook(entity));
            }
        }
        return notebooks;
    }


}