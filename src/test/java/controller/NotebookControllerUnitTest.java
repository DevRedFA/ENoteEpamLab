package controller;


import com.epam.controller.NotebookController;
import com.epam.dto.interfaces.NotebookDtoService;
import com.epam.mapper.NotebookMapper;
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

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NotebookControllerUnitTest {

    @Mock
    private NotebookDtoService notebookService;

    @InjectMocks
    private NotebookController notebookController;

    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(notebookController)
                .build();
    }

    @Test
    public void testGetByUserId() throws Exception {
        mvc.perform(get("/notebooks/1"))
                .andExpect(status().isOk());
        verify(notebookService, times(1)).getByUserId(1);
    }

    @Test
    public void testGetByUserAndNotebookId() throws Exception {
        mvc.perform(get("/notebooks/0/0"))
                .andExpect(status().isOk());
        verify(notebookService, times(1)).getById(0);
    }

    @Test
    public void testGetTestByUserAndNotebookAndTagId() throws Exception {
        mvc.perform(get("/notebooks/2/3/tag/1"))
                .andExpect(status().isOk());
        verify(notebookService, times(1)).getByUserIdAndTagId(2, 1);
    }


    @Test
    public void testPut() throws Exception {
        User user = new User(1, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        Notebook notebook = new Notebook(1, "Notebook 1", user, new HashSet<>());
        String newNotebook = "{\"name\":\"Notebook 1\",\"notes\":[]}";
        mvc.perform(put("/notebooks/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newNotebook))
                .andExpect(status().isOk());
        verify(notebookService, times(1)).save(1, NotebookMapper.toNotebookDto(notebook));
    }

    @Test
    public void testPost() throws Exception {
        User user = new User(1, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        Notebook notebook = new Notebook(0, "Notebook 1", user, new HashSet<>());
        String newNotebook = "{\"name\":\"Notebook 1\",\"notes\":[]}";
        mvc.perform(post("/notebooks/0/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newNotebook))
                .andExpect(status().isOk());
        verify(notebookService, times(1)).update(0, NotebookMapper.toNotebookDto(notebook));
    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/notebooks/0/0"))
                .andExpect(status().isOk());
        doNothing().when(notebookService).delete(0);
        verify(notebookService, times(1)).delete(0);
    }
}