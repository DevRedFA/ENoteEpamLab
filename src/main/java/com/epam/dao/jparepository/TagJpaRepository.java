package com.epam.dao.jparepository;


import com.epam.dao.entity.TagJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagJpaEntity, Long> {

}