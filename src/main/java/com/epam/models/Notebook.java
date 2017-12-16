package com.epam.models;


import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Notebook {

    private int id;

    @NonNull
    private String name;

    @NonNull
    private User user;

    Set<Note> notes;
}
