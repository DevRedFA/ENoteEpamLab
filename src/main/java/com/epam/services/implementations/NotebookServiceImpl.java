package com.epam.services.implementations;

import com.epam.models.Note;
import com.epam.models.Notebook;
import com.epam.models.NotebookRepository;
import com.epam.models.Tag;
import com.epam.services.interfaces.NotebookService;
import com.epam.services.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    NotebookRepository jpaProxyNotebookRepository;

    @Autowired
    private TagService tagService;

    public Notebook save(Notebook notebook) {
        return jpaProxyNotebookRepository.save(notebook);
    }

    @Override
    public Notebook save(long userId, Notebook notebook) {
        return null;
    }

    public List<Notebook> getByUserId(long id) {
        return jpaProxyNotebookRepository.findByUserId(id);
    }

    @Override
    public List<Notebook> getByUserIdAndTagId(long userId, long tagId) {
        List<Notebook> resultNotebooks = new ArrayList<>();
        Tag tag = tagService.getById(tagId);
        List<Notebook> notebooks = this.getByUserId(userId);
        for (Notebook notebook : notebooks) {
            for (Note note : notebook.getNotes()) {
                if (note.getTags().contains(tag)) {
                    resultNotebooks.add(notebook);
                    break;
                }
            }
        }
        return resultNotebooks;
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
    public void update(long notebookId, Notebook notebook) {

    }

    @Override
    public void delete(Notebook notebook) {
        jpaProxyNotebookRepository.delete(notebook);
    }

    @Override
    public void delete(long notebookId) {

    }
}
