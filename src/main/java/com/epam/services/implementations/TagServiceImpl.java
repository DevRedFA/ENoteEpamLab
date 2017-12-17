package com.epam.services.implementations;

import com.epam.dao.jpaproxyrepository.JpaProxyTagRepository;
import com.epam.models.Tag;
import com.epam.models.TagRepository;
import com.epam.services.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository jpaProxyTagRepository;

    public Tag save(Tag tag) {
        return jpaProxyTagRepository.save(tag);
    }

    @Override
    public Tag save(long userId, Tag tag) {
        return null;
    }

    public List<Tag> all() {
        return jpaProxyTagRepository.all();
    }

    @Override
    public List<Tag> getByUserId(long id) {
        return null;
    }

    @Override
    public List<Tag> getByNotebookId(long notebookId) {
        return null;
    }

    @Override
    public List<Tag> getByNoteId(long notebookId) {
        return null;
    }

    public Tag getById(long id) {
        return jpaProxyTagRepository.getById(id);
    }

    public void update(Tag tag) {
        jpaProxyTagRepository.update(tag);
    }

    @Override
    public void update(long id, Tag tag) {

    }

    @Override
    public void delete(long tagId) {
        jpaProxyTagRepository.delete(tagId);
    }
}
