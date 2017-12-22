package controller;


import com.epam.config.AspectJConfig;
import com.epam.config.RootConfig;
import com.epam.config.SwaggerConfig;
import com.epam.config.WebConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class,
        SwaggerConfig.class, WebConfig.class, AspectJConfig.class})
@WebAppConfiguration
public class NotebookControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


    @Test
    public void testGetByUserId() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/notebooks/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse()
                .getContentAsString();
        String result = "[{\"name\":\"notebook 1\",\"notes\":[{\"name\":\"note 2\",\"text\":\"text 2\",\"tags\":[],\"created\":\"2017-11-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 8\",\"text\":\"text 8\",\"tags\":[],\"created\":\"2017-11-12\",\"updated\":\"2017-12-12\"}]},{\"name\":\"notebook 2\",\"notes\":[{\"name\":\"note 9\",\"text\":\"text 9\",\"tags\":[],\"created\":\"2017-10-12\",\"updated\":\"2017-11-12\"},{\"name\":\"note 3\",\"text\":\"text 3\",\"tags\":[],\"created\":\"2017-10-12\",\"updated\":\"2017-11-12\"}]},{\"name\":\"Notebook 1\",\"notes\":[]}]";
        assertEquals(result, contentAsString);
    }

    @Test
    public void testGetByUserAndTagId() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/notebooks/0/0"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse()
                .getContentAsString();
        String result = "{\"name\":\"notebook 0\",\"notes\":[{\"name\":\"note 0\",\"text\":\"text 0\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 6\",\"text\":\"text 6\",\"tags\":[],\"created\":\"2017-11-12\",\"updated\":\"2017-12-12\"}]}";
        assertEquals(result, contentAsString);
    }

    @Test
    public void testGetTestByUserAndNotebookAndTagId() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/notebooks/2/3/tag/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse()
                .getContentAsString();
        String result = "[{\"name\":\"notebook 3\",\"notes\":[{\"name\":\"note 4\",\"text\":\"text 4\",\"tags\":[{\"name\":\"tag1\"},{\"name\":\"tag2\"}],\"created\":\"2017-11-12\",\"updated\":\"2017-12-12\"}]}]";
        assertEquals(result, contentAsString);
    }


    @Test
    public void testPut() throws Exception {
        String newNotebook = "{\"name\":\"Notebook 1\",\"notes\":[]}";
        mvc.perform(put("/notebooks/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newNotebook))
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    public void testPost() throws Exception {
        String newNotebook = "{\"name\":\"Notebook 2\",\"notes\":[]}";
        mvc.perform(post("/notebooks/0/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newNotebook))
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/notebooks/0/0"))
                .andExpect(status().isOk());
    }
}