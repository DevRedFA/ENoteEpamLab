package com.epam.dto.implementations;

import com.epam.dto.TagDto;
import com.epam.mapper.TagMapper;
import com.epam.service.models.Tag;
import com.epam.dto.interfaces.TagDtoService;
import com.epam.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TagDtoServiceImpl implements TagDtoService {


    @Autowired
    private TagService tagService;

    @Override
    public TagDto save(TagDto tagDto) {
        Tag tag = tagService.save(TagMapper.toTag(tagDto));
        return TagMapper.toTagDto(tag);
    }

    @Override
    public TagDto save(long userId, TagDto tagDto) {
        Tag tag = tagService.save(userId, TagMapper.toTag(tagDto));
        return TagMapper.toTagDto(tag);
    }

    @Override
    public List<TagDto> all() {
        return TagMapper.toTagDtos(tagService.all());
    }

    @Override
    public List<TagDto> getByUserId(long userId) {
        return TagMapper.toTagDtos(tagService.getByUserId(userId));
    }

    @Override
    public List<TagDto> getByNotebookId(long notebookId) {
        return TagMapper.toTagDtos(tagService.getByNotebookId(notebookId));
    }

    @Override
    public List<TagDto> getByNoteId(long noteId) {
        return TagMapper.toTagDtos(tagService.getByNoteId(noteId));
    }

    @Override
    public TagDto getById(long tagId) {
        return TagMapper.toTagDto(tagService.getById(tagId));
    }

    @Override
    public void update(TagDto tagDto) {
        tagService.update(TagMapper.toTag(tagDto));
    }

    @Override
    public void update(long tagId, TagDto tagDto) {
        tagService.update(tagId, TagMapper.toTag(tagDto));
    }

    @Override
    public void delete(long tagId) {
        tagService.delete(tagId);
    }
}
