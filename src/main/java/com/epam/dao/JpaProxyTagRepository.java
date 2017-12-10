package com.epam.dao;

import com.epam.model.Tag;
import com.epam.model.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaProxyTagRepository implements TagRepository {

    @Autowired
    private TagJpaRepository jpaRepository;

    public Tag save(Tag newTag) {
        TagJpaEntity newEntity = new TagJpaEntity(newTag);
        TagJpaEntity savedEntity = jpaRepository.save(newEntity);
        return savedEntity.toTag();
    }

    public void update(Tag tag) {
        TagJpaEntity newEntity = new TagJpaEntity(tag);
        // is this really needed?
        jpaRepository.save(newEntity);
    }


    public List<Tag> all() {
        List<TagJpaEntity> entities = jpaRepository.findAll();
        List<Tag> tags = new ArrayList<Tag>(entities.size());
        for (TagJpaEntity entity : entities) {
            Tag tag = entity.toTag();
            tags.add(tag);
        }
        return tags;
    }

    public Tag getById(int id) {
        List<TagJpaEntity> entities = jpaRepository.findAll();
        List<Tag> tags = new ArrayList<Tag>(entities.size());
        for (TagJpaEntity entity : entities) {
            Tag tag = entity.toTag();
            if(tag.getId() == id) {
                return tag;
            }
        }
        return null;
    }
}