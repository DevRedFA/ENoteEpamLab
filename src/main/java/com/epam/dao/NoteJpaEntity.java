package com.epam.dao;


import com.epam.model.Note;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "tags", schema = "public")
public class NoteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false)
    private int id;

    @NonNull
    @Column(name = "text",
            nullable = false,
            length = -1)
    private String text;

    @JoinColumn(name = "notebook_id",
            referencedColumnName = "id",
            nullable = false)
    private int notebookId;

    @NonNull
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false)
    private int userId;

    @NonNull
    @Column(name = "created",
            insertable = false,
            updatable = false,
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @NonNull
    @Column(name = "updated",
            insertable = false,
            updatable = false,
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;


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
    private Set<NoteJpaEntity> notes;


    NoteJpaEntity(final Note note) {
        this.id = note.getId();
        this.text = note.getText();
        this.notebookId = note.getNotebookId();
        this.userId = note.getUserId();
    }

    public Note toNote() {
        Note note = new Note();
        note.setId(id);
        note.setText(text);
        note.setNotebookId(notebookId);
        note.setUserId(userId);
        return note;
    }
}
