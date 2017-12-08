package com.epam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Note {

    private int id;
    @NonNull
    private String name;
    @NonNull
    private String text;
    private int notebookId;
    private int userId;
    @NonNull
    private Date created;
    @NonNull
    private Date updated;
}
