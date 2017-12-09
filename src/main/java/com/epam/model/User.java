package com.epam.model;

import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    private int id;
    @NonNull
    private String name;
    @NonNull
    private String password;

    private Set<Tag> tags;

    private Set<Notebook> notebooks;
}
