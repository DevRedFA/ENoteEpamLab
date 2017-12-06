package com.epam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor

public class User {

    private int id;
    @NonNull
    private String name;
    @NonNull
    private String password;

    private Set<Tag> tags;
}
