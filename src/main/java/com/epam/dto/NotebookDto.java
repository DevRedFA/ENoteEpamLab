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
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class NotebookDto {

  @NonNull
  private String name;

  @NonNull
  Set<NoteDto> notes;
}
