package controller;


import com.epam.controller.TagController;
import com.epam.dto.TagDto;
import com.epam.dto.interfaces.TagDtoService;
import com.epam.mapper.TagMapper;
import com.epam.service.models.Tag;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TagControllerUnitTest {


    @Mock
    private TagDtoService tagService;

    @InjectMocks
    private TagController tagController;

    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(tagController)
                .build();
    }

    @Test
    public void testGet() throws Exception {
        mvc.perform(get("/tags/0"))
                .andExpect(status().isOk());
        verify(tagService, times(1)).getByUserId(0);
    }

    @Test
    public void testGetByUserNotebookNoteId() throws Exception {
        mvc.perform(get("/tags/0/0/0"))
                .andExpect(status().isOk());
        verify(tagService, times(1)).getByNoteId(0);
    }

    @Ignore
    @Test
    public void testPut() throws Exception {
        Tag tag = new Tag("new tag");
        TagDto tagDto = TagMapper.toTagDto(tag);
        when(tagService.save(0, tagDto)).thenReturn(tagDto);
        String newTag = "[{\"name\":\"new tag\"}]";
        mvc.perform(put("/tags")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newTag))
                .andExpect(status().isOk());
        verify(tagService, times(0)).save(0, tagDto);
    }

    @Ignore
    @Test
    public void testPost() throws Exception {
        Tag tag = new Tag("new tag");
        TagDto tagDto = TagMapper.toTagDto(tag);
        when(tagService.save(0, tagDto)).thenReturn(tagDto);
        String newTag = "[{\"name\":\"new tag\"}]";
        mvc.perform(post("/tags/0/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newTag))
                .andExpect(status().isOk());
        verify(tagService, times(0)).save(0, tagDto);
    }

    @Test
    public void testDelete() throws Exception {
        Tag tag = new Tag("new tag");
        TagDto tagDto = TagMapper.toTagDto(tag);
        doNothing().when(tagService).delete(0);
        mvc.perform(delete("/tags/0/0"))
                .andExpect(status().isOk());
        verify(tagService, times(1)).delete(0);
    }
}