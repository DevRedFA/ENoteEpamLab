package controller;


import static org.junit.Assert.assertEquals;

import com.epam.dto.NoteDto;
import com.epam.dto.NotebookDto;
import com.epam.dto.TagDto;
import com.epam.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class UserControllerTest {

  @Test
  public void testConversion() throws IOException {
    Set<TagDto> tags = new HashSet<>();
    tags.add(new TagDto("tag1"));
    Set<NoteDto> notes = new HashSet<>();
    notes.add(new NoteDto("name", "text", tags));
    Set<NotebookDto> notebookDtos = new HashSet<>();
    notebookDtos.add(new NotebookDto("notebok1", notes));
    UserDto userDto = UserDto.builder()
                             .name("Ivan")
                             .password("password")
                             .notebooks(notebookDtos)
                             .tags(new HashSet<>())
                             .build();
    String userDtoString = new ObjectMapper().writeValueAsString(userDto);
    UserDto userDto1 = new ObjectMapper().readValue(userDtoString, UserDto.class);
    assertEquals(userDto, userDto1);
  }
}
