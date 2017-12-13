package com.epam.models;


import java.util.Set;
import lombok.*;

@Data
@RequiredArgsConstructor
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
