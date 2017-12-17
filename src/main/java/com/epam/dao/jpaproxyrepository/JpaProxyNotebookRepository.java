package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.dao.jparepository.NotebookJpaRepository;
import com.epam.dao.mapper.NotebookMapper;
import com.epam.models.Notebook;
import com.epam.models.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaProxyNotebookRepository implements NotebookRepository {

    @Autowired
    private NotebookJpaRepository jpaRepository;

    @Autowired
    private NotebookMapper notebookMapper;

    @Override
    public Notebook save(Notebook notebook) {
        NotebookJpaEntity savedEntity = jpaRepository.save(notebookMapper.notebookToNotebookEntity(notebook));
        return notebookMapper.notebookEntityToNotebook(savedEntity);
    }

    @Override
    public void update(Notebook notebook) {
        jpaRepository.save(notebookMapper.notebookToNotebookEntity(notebook));
    }

    @Override
    public List<Notebook> all() {
        return notebookMapper.notebookEntitiesToNotebooks(jpaRepository.findAll());
    }

    @Override
    public List<Notebook> findByUserId(long userId) {
        return notebookMapper.notebookEntitiesToNotebooks(jpaRepository.findByUser_Id(userId));
    }

    @Override
    public void delete(Notebook notebook) {
        jpaRepository.delete((long) notebook.getId());
    }

    @Override
    public void delete(long notebookId) {
        jpaRepository.delete(notebookId);
    }

    @Override
    public Notebook getById(long id) {
        return notebookMapper.notebookEntityToNotebook(jpaRepository.getOne(id));
    }
}