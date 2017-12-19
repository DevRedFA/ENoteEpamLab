package com.epam.dto;

import java.util.Set;

import com.epam.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class UserDto {

    private String name;

    private String password;

    private Set<TagDto> tags;

    private Set<NotebookDto> notebooks;
}
