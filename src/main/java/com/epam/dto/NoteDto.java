package com.epam.dto;


import java.sql.Date;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class NoteDto {

    private String name;

    private String text;

    private Set<TagDto> tags;

    private Date created;

    private Date updated;
}
