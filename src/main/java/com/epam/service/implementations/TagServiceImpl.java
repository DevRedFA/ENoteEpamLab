package com.epam.service.implementations;

import com.epam.service.models.*;
import com.epam.service.interfaces.NoteService;
import com.epam.service.interfaces.NotebookService;
import com.epam.service.interfaces.TagService;
import com.epam.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository jpaProxyTagRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    public Tag save(Tag tag) {
        return jpaProxyTagRepository.save(tag);
    }

    @Override
    public Tag save(long userId, Tag tag) {
        User user = userService.getById(userId);
        user.getTags().add(tag);
        userService.update(user);
        //TODO: something strange, need to think
        Tag resultTag = null;
        List<Tag> tags = this.getByUserId(userId);
        for (Tag tag1 : tags) {
            if (tag1.equals(tag)) {
                resultTag = tag1;
            }
        }
        return resultTag;
    }

    public List<Tag> all() {
        return jpaProxyTagRepository.all();
    }

    @Override
    public List<Tag> getByUserId(long userId) {
        return new ArrayList<>(userService.getById(userId).getTags());
    }

    @Override
    public List<Tag> getByNotebookId(long notebookId) {
        Notebook notebook = notebookService.getById(notebookId);
        Set<Tag> tags = new HashSet<>();
        for (Note note : notebook.getNotes()) {
            tags.addAll(note.getTags());
        }
        return new ArrayList<>(tags);
    }

    @Override
    public List<Tag> getByNoteId(long noteId) {
        return new ArrayList<>(noteService.getById(noteId).getTags());
    }

    public Tag getById(long id) {
        return jpaProxyTagRepository.getById(id);
    }

    public void update(Tag tag) {
        jpaProxyTagRepository.update(tag);
    }

    @Override
    public void update(long id, Tag tag) {
        Tag oldTag = jpaProxyTagRepository.getById(id);
        //TODO: check fields for null
        oldTag.setName(tag.getName());
        jpaProxyTagRepository.update(tag);
    }

    @Override
    public void delete(long tagId) {
        jpaProxyTagRepository.delete(tagId);
    }
}
