package com.epam.dao.entity;

import com.epam.models.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "users", schema = "public")
public class UserJpaEntity {

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

    @NonNull
    @Column(name = "password",
            nullable = false,
            length = -1)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_tags",
            schema = "public",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id",
                    nullable = false))
    private Set<TagJpaEntity> tags;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NotebookJpaEntity> notebooks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NoteJpaEntity> notes;
}
