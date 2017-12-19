package com.epam.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto {

  @NonNull
  private String name;

  @NonNull
  private String password;

  @NonNull
  private Set<TagDto> tags;

  @NonNull
  private Set<NotebookDto> notebooks;

  //    private Set<NoteDto> notes;


}
