package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.TagJpaEntity;
import com.epam.dao.jparepository.TagJpaRepository;
import com.epam.mapper.TagMapper;
import com.epam.service.models.Tag;
import com.epam.service.models.TagRepository;
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

    @Override
    public Tag save(Tag newTag) {
        TagJpaEntity savedEntity = jpaRepository.save(TagMapper.toTagJpaEntity(newTag));
        return TagMapper.toTag(savedEntity);
    }

    @Override
    public void update(Tag tag) {
        jpaRepository.save(TagMapper.toTagJpaEntity(tag));
    }

    @Override
    public List<Tag> all() {
        return TagMapper.toTags(jpaRepository.findAll());
    }

    @Override
    public Tag getById(long id) {
        return TagMapper.toTag(jpaRepository.getOne(id));
    }

    @Override
    public void delete(long tagId) {
        jpaRepository.delete(tagId);
    }
}