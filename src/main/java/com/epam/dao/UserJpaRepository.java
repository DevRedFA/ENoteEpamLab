package com.epam.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    List<UserJpaEntity> findAllByName(String carter);
}