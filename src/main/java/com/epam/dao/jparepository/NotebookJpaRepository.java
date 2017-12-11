package com.epam.dao.jparepository;


import com.epam.dao.entity.NotebookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookJpaRepository extends JpaRepository<NotebookJpaEntity, Long> {

}