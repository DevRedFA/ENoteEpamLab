package com.epam.dto.implementations;

import com.epam.dto.NotebookDto;
import com.epam.mapper.NotebookMapper;
import com.epam.service.models.Notebook;
import com.epam.dto.interfaces.NotebookDtoService;
import com.epam.service.interfaces.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NotebookDtoServiceImpl implements NotebookDtoService {


    @Autowired
    private NotebookService notebookService;

    @Override
    public NotebookDto save(NotebookDto notebookDto) {
        Notebook notebook = notebookService.save(NotebookMapper.toNotebook(notebookDto));
        return NotebookMapper.toNotebookDto(notebook);
    }

    @Override
    public NotebookDto save(long userId, NotebookDto notebookDto) {
        Notebook notebook = notebookService.save(userId, NotebookMapper.toNotebook(notebookDto));
        return NotebookMapper.toNotebookDto(notebook);
    }

    @Override
    public List<NotebookDto> getByUserId(long userId) {
        return NotebookMapper.toNotebookDtos(notebookService.getByUserId(userId));
    }

    @Override
    public List<NotebookDto> getByUserIdAndTagId(long userId, long tagId) {
        return NotebookMapper.toNotebookDtos(notebookService.getByUserIdAndTagId(userId, tagId));
    }

    @Override
    public NotebookDto getById(long notebookDtoId) {
        return NotebookMapper.toNotebookDto(notebookService.getById(notebookDtoId));
    }

    @Override
    public List<NotebookDto> all() {
        return NotebookMapper.toNotebookDtos(notebookService.all());
    }

    @Override
    public void update(NotebookDto notebookDto) {
        notebookService.update(NotebookMapper.toNotebook(notebookDto));
    }

    @Override
    public void update(long notebookDtoId, NotebookDto notebookDto) {
        notebookService.update(notebookDtoId, NotebookMapper.toNotebook(notebookDto));
    }

    @Override
    public void delete(NotebookDto notebookDto) {
        notebookService.delete(NotebookMapper.toNotebook(notebookDto));
    }

    @Override
    public void delete(long notebookDtoId) {
        notebookService.delete(notebookDtoId);
    }
}
