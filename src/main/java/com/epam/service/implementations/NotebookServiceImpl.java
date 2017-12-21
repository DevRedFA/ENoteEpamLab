package com.epam.service.implementations;

import com.epam.service.interfaces.NotebookService;
import com.epam.service.interfaces.TagService;
import com.epam.service.interfaces.UserService;
import com.epam.service.models.*;
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

    @Autowired
    private UserService userService;

    public Notebook save(Notebook notebook) {
        return jpaProxyNotebookRepository.save(notebook);
    }

    @Override
    public Notebook save(long userId, Notebook notebook) {
        User user = userService.getById(userId);
        user.getNotebooks().add(notebook);
        notebook.setUser(user);
        userService.update(user);
        //TODO: something strange, need to think
        Notebook resultNotebook = null;
        List<Notebook> notebooks = this.getByUserId(userId);
        for (Notebook notebook1 : notebooks) {
            if (notebook1.equals(notebook)) {
                resultNotebook = notebook1;
            }
        }
        return resultNotebook;
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
        Notebook oldNotebook = getById(notebookId);
        //TODO: check fields for null
        oldNotebook.setName(notebook.getName());
        oldNotebook.setNotes(notebook.getNotes());
        oldNotebook.setUser(notebook.getUser());
        update(notebook);
    }

    @Override
    public void delete(Notebook notebook) {
        jpaProxyNotebookRepository.delete(notebook);
    }

    @Override
    public void delete(long notebookId) {
        jpaProxyNotebookRepository.delete(notebookId);
    }
}
