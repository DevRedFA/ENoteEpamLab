package com.epam.dao.jparepository;

import com.epam.dao.entity.NoteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<NoteJpaEntity, Long> {


    List<NoteJpaEntity> findAllByUserId(long userId);
}