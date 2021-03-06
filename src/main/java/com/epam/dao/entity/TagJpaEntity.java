package com.epam.dao.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "tags", schema = "public")
public class TagJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private long id;

    @NonNull
    @Column(name = "name",
            nullable = false,
            length = -1)
    private String name;
}
