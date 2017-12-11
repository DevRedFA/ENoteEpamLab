package com.epam.dao.entity;


import com.epam.models.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "tags", schema = "public")
public class TagJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false)
    private int id;

    @NonNull
    @Column(name = "name",
            nullable = false,
            length = -1)
    private String name;
}
