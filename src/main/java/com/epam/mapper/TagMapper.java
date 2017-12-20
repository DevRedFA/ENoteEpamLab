package com.epam.mapper;

import com.epam.dao.entity.TagJpaEntity;
import com.epam.dto.TagDto;
import com.epam.service.models.Tag;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class TagMapper {

    private TagMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static Tag toTag(TagDto tagDto) {
        return modelMapper.map(tagDto, Tag.class);
    }

    public static TagDto toTagDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }

    public static Tag toTag(TagJpaEntity tagJpaEntity) {
        return modelMapper.map(tagJpaEntity, Tag.class);
    }

    public static TagJpaEntity toTagJpaEntity(Tag tag) {
        return modelMapper.map(tag, TagJpaEntity.class);
    }

    public static List<TagJpaEntity> toTagJpaEntities(List<Tag> tags) {
        List<TagJpaEntity> list = new ArrayList<>();
        for (Tag tag : tags) {
            list.add(modelMapper.map(tag, TagJpaEntity.class));
        }
        return list;
    }

    public static List<Tag> toTags(List<TagJpaEntity> tags) {
        List<Tag> list = new ArrayList<>();
        for (TagJpaEntity tag : tags) {
            list.add(modelMapper.map(tag, Tag.class));
        }
        return list;
    }

    public static List<TagDto> toTagDtos(List<Tag> tags) {
        List<TagDto> list = new ArrayList<>();
        for (Tag tag : tags) {
            list.add(modelMapper.map(tag, TagDto.class));
        }
        return list;
    }
}
