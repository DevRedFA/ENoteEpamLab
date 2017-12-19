package com.epam.dto;


import java.sql.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NoteDto {

  @NonNull
  private String name;

  @NonNull
  private String text;


  @NonNull
  private Set<TagDto> tags;

  @NonNull
  private Date created;

  @NonNull
  private Date updated;
}
