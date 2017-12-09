package com.epam.dao;


import com.epam.model.Note;
import com.epam.model.Notebook;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
@EqualsAndHashCode(of = "id")
@Table(name = "notebooks", schema = "public")
public class NotebookJpaEntity {

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private UserJpaEntity user;

    @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NoteJpaEntity> notes;

    NotebookJpaEntity(final Notebook notebook) {
        this.id = notebook.getId();
        this.name = notebook.getName();
    }

    public Notebook toNotebook() {
        Notebook notebook = new Notebook();
        notebook.setId(id);
        notebook.setName(name);
        return notebook;
    }
}
