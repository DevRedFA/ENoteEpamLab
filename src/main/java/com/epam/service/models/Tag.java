package com.epam.service.models;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tag {

    private long id;
    @NonNull
    private String name;
}
