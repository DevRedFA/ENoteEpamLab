package com.epam.dao;


import com.epam.model.Tag;
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

    TagJpaEntity(final Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public Tag toTag() {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        return tag;
    }
}
