package com.epam.mapper;

import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.dto.NotebookDto;
import com.epam.service.models.Notebook;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class NotebookMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    private NotebookMapper() {
    }

    public static Notebook toNotebook(NotebookDto notebookDto) {
        return modelMapper.map(notebookDto, Notebook.class);
    }

    public static NotebookDto toNotebookDto(Notebook notebook) {
        return modelMapper.map(notebook, NotebookDto.class);
    }

    public static Notebook toNotebook(NotebookJpaEntity notebookJpaEntity) {
        return modelMapper.map(notebookJpaEntity, Notebook.class);
    }

    public static NotebookJpaEntity toNotebookJpaEntity(Notebook notebook) {
        return modelMapper.map(notebook, NotebookJpaEntity.class);
    }

    public static List<NotebookJpaEntity> toNotebookJpaEntities(List<Notebook> notebooks) {
        List<NotebookJpaEntity> list = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            list.add(modelMapper.map(notebook, NotebookJpaEntity.class));
        }
        return list;
    }

    public static List<Notebook> toNotebooks(List<NotebookJpaEntity> notebooks) {
        List<Notebook> list = new ArrayList<>();
        for (NotebookJpaEntity notebook : notebooks) {
            list.add(modelMapper.map(notebook, Notebook.class));
        }
        return list;
    }

    public static List<NotebookDto> toNotebookDtos(List<Notebook> notebooks) {
        List<NotebookDto> list = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            list.add(modelMapper.map(notebook, NotebookDto.class));
        }
        return list;
    }
}
