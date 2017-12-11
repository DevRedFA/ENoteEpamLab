package com.epam.dao.mapper;

import com.epam.dao.entity.TagJpaEntity;
import com.epam.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag tagEntityToTag(TagJpaEntity tag);

    List<Tag> tagEntitiesToTags(List<TagJpaEntity> tags);

    TagJpaEntity tagToTagEntity(Tag tag);

    List<TagJpaEntity> tagsToTagEntities(List<Tag> tags);
}
