package com.epam.models;


import lombok.*;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
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

    private Date created;

    private Date updated;
}
