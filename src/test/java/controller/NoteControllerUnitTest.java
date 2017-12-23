package controller;


import com.epam.controller.NoteController;
import com.epam.dto.NoteDto;
import com.epam.dto.interfaces.NoteDtoService;
import com.epam.mapper.NoteMapper;
import com.epam.service.models.Note;
import com.epam.service.models.Notebook;
import com.epam.service.models.Tag;
import com.epam.service.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class NoteControllerUnitTest {

    @Mock
    private NoteDtoService noteService;

    @InjectMocks
    private NoteController noteController;

    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(noteController)
                .build();
    }


    @Test
    public void testGetByUserId() throws Exception {
        mvc.perform(get("/notes/0"))
                .andExpect(status().isOk());
        verify(noteService, times(1)).getByUserId(0);
    }

    @Test
    public void testGetByUserAndTagId() throws Exception {
        mvc.perform(get("/notes/0/tag/0"))
                .andExpect(status().isOk());
        verify(noteService, times(1)).getByUserIdAndTagId(0, 0);
    }

    @Test
    public void testGetTestByUserAndNotebookAndNoteId() throws Exception {
        mvc.perform(get("/notes/0/0/0"))
                .andExpect(status().isOk());
        verify(noteService, times(1)).getById(0);
    }

    @Test
    public void testGetTestByUserAndNotebookAndTagId() throws Exception {
        mvc.perform(get("/notes/0/0/tag/0"))
                .andExpect(status().isOk());
        verify(noteService, times(1)).getByNotebookIdAndTagId(0, 0);
    }


    @Test
    public void testPut() throws Exception {
        User user = new User(1, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        Note note = new Note(3, "note", "text", user, new Notebook(),
                new HashSet<>(), null, null);
        NoteDto noteDto = NoteMapper.toNoteDto(note);
        String newNote = "{\"name\":\"note\",\"text\":\"text\",\"tags\":[]}";
        mvc.perform(put("/notes/1/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newNote))
                .andExpect(status().isOk());
        verify(noteService, times(1)).save(3, noteDto);
    }

    @Test
    public void testPost() throws Exception {
        User user = new User(1, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        Note note = new Note(3, "note", "text", user, new Notebook(),
                new HashSet<>(), null, null);
        NoteDto noteDto = NoteMapper.toNoteDto(note);
        String newNote = "{\"name\":\"note\",\"text\":\"text\",\"tags\":[]}";
        mvc.perform(post("/notes/0/0/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newNote))
                .andExpect(status().isOk());
        verify(noteService, times(1)).update(0, noteDto);
    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/notes/0/0/0"))
                .andExpect(status().isOk());
        doNothing().when(noteService).delete(0);
    }
}