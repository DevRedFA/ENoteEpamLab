package com.epam.dao.jparepository;


import com.epam.dao.entity.NotebookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookJpaRepository extends JpaRepository<NotebookJpaEntity, Long> {

    List<NotebookJpaEntity> findAllByUserId(long userId);

}