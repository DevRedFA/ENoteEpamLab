package com.epam.dao.jparepository;


import com.epam.dao.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    List<UserJpaEntity> findAllByName(String carter);
}