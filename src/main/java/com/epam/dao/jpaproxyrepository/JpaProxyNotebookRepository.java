package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.dao.jparepository.NotebookJpaRepository;
import com.epam.mapper.NotebookMapper;
import com.epam.service.models.Notebook;
import com.epam.service.models.NotebookRepository;
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


    @Override
    public Notebook save(Notebook notebook) {
        NotebookJpaEntity savedEntity = jpaRepository.save(NotebookMapper.toNotebookJpaEntity(notebook));
        return NotebookMapper.toNotebook(savedEntity);
    }

    @Override
    public void update(Notebook notebook) {
        jpaRepository.save(NotebookMapper.toNotebookJpaEntity(notebook));
    }

    @Override
    public List<Notebook> all() {
        return NotebookMapper.toNotebooks(jpaRepository.findAll());
    }

    @Override
    public List<Notebook> findByUserId(long userId) {
        return NotebookMapper.toNotebooks(jpaRepository.findByUser_Id(userId));
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
        return NotebookMapper.toNotebook(jpaRepository.getOne(id));
    }
}