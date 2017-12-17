package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.TagJpaEntity;
import com.epam.dao.jparepository.TagJpaRepository;
import com.epam.dao.mapper.TagMapper;
import com.epam.models.Tag;
import com.epam.models.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaProxyTagRepository implements TagRepository {

    @Autowired
    private TagJpaRepository jpaRepository;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag save(Tag newTag) {
        TagJpaEntity savedEntity = jpaRepository.save(tagMapper.tagToTagEntity(newTag));
        return tagMapper.tagEntityToTag(savedEntity);
    }

    @Override
    public void update(Tag tag) {
        jpaRepository.save(tagMapper.tagToTagEntity(tag));
    }

    @Override
    public List<Tag> all() {
        return tagMapper.tagEntitiesToTags(jpaRepository.findAll());
    }

    @Override
    public Tag getById(long id) {
        return tagMapper.tagEntityToTag(jpaRepository.getOne(id));
    }

    @Override
    public void delete(long tagId) {
        jpaRepository.delete(tagId);
    }
}