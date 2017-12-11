package com.epam.dao.mapper;

import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.models.Notebook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface NotebookMapper {

    NotebookMapper INSTANCE = Mappers.getMapper(NotebookMapper.class);

    Notebook notebookEntityToNotebook(NotebookJpaEntity notebook);

    List<Notebook> notebookEntitiesToNotebooks(List<NotebookJpaEntity> notebooks);

    NotebookJpaEntity notebookToNotebookEntity(Notebook notebook);

    List<NotebookJpaEntity> notebooksToNotebookEntities(List<Notebook> notebookss);
}
