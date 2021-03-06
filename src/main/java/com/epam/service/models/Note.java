package com.epam.service.models;


import lombok.*;

import java.sql.Date;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "notebook"})
@ToString(exclude = {"notebook", "user"})
public class Note {

    private int id;

    @NonNull
    private String name;

    @NonNull
    private String text;

    @NonNull
    private User user;

    @NonNull
    private Notebook notebook;

    private Set<Tag> tags;

    private Date created;

    private Date updated;
}
