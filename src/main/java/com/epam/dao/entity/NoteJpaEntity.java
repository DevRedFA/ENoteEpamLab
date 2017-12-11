package com.epam.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "notebook"})
@EqualsAndHashCode(of = "id")
@Table(name = "notes", schema = "public")
public class NoteJpaEntity {

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
    @Column(name = "text",
            nullable = false,
            length = -1)
    private String text;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "notes_tags",
            schema = "public",
            joinColumns = @JoinColumn(
                    name = "note_id",
                    referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id",
                    nullable = false))
    private Set<TagJpaEntity> tags;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private UserJpaEntity user;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notebook_id",
            referencedColumnName = "id",
            nullable = false)
    private NotebookJpaEntity notebook;

    @Column(name = "created_date")
    private Date created;

    @Column(name = "updated_date")
    private Date updated;
}
