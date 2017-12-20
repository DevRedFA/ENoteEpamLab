package com.epam.service.models;

import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"notes"})
public class User {

    private long id;
    @NonNull
    private String name;
    @NonNull
    private String password;

    private Set<Tag> tags;

    private Set<Notebook> notebooks;

    private Set<Note> notes;
}
