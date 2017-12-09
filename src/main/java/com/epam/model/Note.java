package com.epam.model;


import lombok.*;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private int id;

    private String name;

    @NonNull
    private String text;

    @NonNull
    private int user_id;

    @NonNull
    private int notebook_id;

    private Date created;

    private Date updated;
}
