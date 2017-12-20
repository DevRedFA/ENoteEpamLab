package com.epam.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UserDto {

    private String name;

    private String password;

    private Set<TagDto> tags;

    private Set<NotebookDto> notebooks;
}
