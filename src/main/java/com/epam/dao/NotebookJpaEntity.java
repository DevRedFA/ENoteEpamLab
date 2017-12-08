package com.epam.dao;


import com.epam.model.Notebook;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
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

    @OneToMany
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private int userId;

    NotebookJpaEntity(final Notebook notebook) {
        this.id = notebook.getId();
        this.name = notebook.getName();
        this.userId = notebook.getUserId();
    }

    public Notebook toNotebook() {
        Notebook notebook = new Notebook();
        notebook.setId(id);
        notebook.setName(name);
        notebook.setUserId(userId);
        return notebook;
    }
}
