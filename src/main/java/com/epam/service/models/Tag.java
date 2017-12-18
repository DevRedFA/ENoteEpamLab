package com.epam.service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

    @JsonIgnore
    private long id;

    @NonNull
    private String name;
}
