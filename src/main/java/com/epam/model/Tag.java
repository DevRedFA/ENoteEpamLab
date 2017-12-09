package com.epam.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tag {

    private int id;
    @NonNull
    private String name;
}
