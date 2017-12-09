package com.epam.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Notebook {

    private int id;

    @NonNull
    private String name;

    @NonNull
    private int user_id;
}
