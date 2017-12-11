package com.epam.dao.jparepository;

import com.epam.dao.entity.NoteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteJpaRepository extends JpaRepository<NoteJpaEntity, Long> {

}