package com.epam.services.implementations;

import com.epam.dao.JpaProxyTagRepository;
import com.epam.model.Tag;
import com.epam.services.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    JpaProxyTagRepository jpaProxyTagRepository;

    public Tag save(Tag tag) {
        return jpaProxyTagRepository.save(tag);
    }

    public List<Tag> all() {
        return jpaProxyTagRepository.all();
    }

    public Tag getById(int id) {
        return jpaProxyTagRepository.getById(id);
    }

    public void update(Tag tag) {
        jpaProxyTagRepository.update(tag);
    }
}
