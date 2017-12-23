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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class NoteControllerTest {

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
    MvcResult mvcResult = mvc.perform(get("/notes/0"))
                             .andExpect(status().isOk())
                             .andReturn();
    String contentAsString = mvcResult.getResponse()
                                      .getContentAsString();
    String result = "[{\"name\":\"note 0\",\"text\":\"text 0\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 1\",\"text\":\"text 1\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 6\",\"text\":\"text 6\",\"tags\":[],\"created\":\"2017-11-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 7\",\"text\":\"text 7\",\"tags\":[],\"created\":\"2017-10-12\",\"updated\":\"2017-11-12\"}]";
    assertEquals(result, contentAsString);
  }

  @Test
  public void testGetByUserAndTagId() throws Exception {
    MvcResult mvcResult = mvc.perform(get("/notes/0/tag/0"))
                             .andExpect(status().isOk())
                             .andReturn();
    String contentAsString = mvcResult.getResponse()
                                      .getContentAsString();
    String result = "[{\"name\":\"note 0\",\"text\":\"text 0\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 1\",\"text\":\"text 1\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"}]";
    assertEquals(result, contentAsString);
  }

  @Ignore
  @Test
  public void testGetTestByUserAndNotebookId() throws Exception {
    MvcResult mvcResult = mvc.perform(get("/notes/0/0"))
                             .andExpect(status().isOk())
                             .andReturn();
    String contentAsString = mvcResult.getResponse()
                                      .getContentAsString();
    String result = ":[{\"name\":\"note 0\",\"text\":\"text 0\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"},{\"name\":\"note 6\",\"text\":\"text 6\",\"tags\":[],\"created\":\"2017-11-12\",\"updated\":\"2017-12-12\"}]";
    assertEquals(result, contentAsString);
  }

  @Test
  public void testGetTestByUserAndNotebookAndNoteId() throws Exception {
    MvcResult mvcResult = mvc.perform(get("/notes/0/0/0"))
                             .andExpect(status().isOk())
                             .andReturn();
    String contentAsString = mvcResult.getResponse()
                                      .getContentAsString();
    String result = "{\"name\":\"note 0\",\"text\":\"text 0\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"}";
    assertEquals(result, contentAsString);
  }

  @Test
  public void testGetTestByUserAndNotebookAndTagId() throws Exception {
    MvcResult mvcResult = mvc.perform(get("/notes/0/0/tag/0"))
                             .andExpect(status().isOk())
                             .andReturn();
    String contentAsString = mvcResult.getResponse()
                                      .getContentAsString();
    String result = "[{\"name\":\"note 0\",\"text\":\"text 0\",\"tags\":[{\"name\":\"tag0\"}],\"created\":\"2017-12-12\",\"updated\":\"2017-12-12\"}]";
    assertEquals(result, contentAsString);
  }


  @Test
  public void testPut() throws Exception {
    String newNote = "{\"name\":\"Note 1\",\"text\":\"Text 1\",\"tags\":[],\"created\":\"2017-12-21\",\"updated\":\"2017-12-21\"}";
    mvc.perform(put("/notes/1/3")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(newNote))
       .andExpect(status().isOk());
  }

  @Ignore
  @Test
  public void testPost() throws Exception {
    String newNote = "{\"name\":\"Note 1\",\"text\":\"Text 1\",\"tags\":[],\"created\":\"2017-12-21\",\"updated\":\"2017-12-21\"}";
    mvc.perform(post("/notes/0/0/0")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(newNote))
       .andExpect(status().isOk());
  }

  @Ignore
  @Test
  public void testDelete() throws Exception {
    mvc.perform(delete("/notes/0/0/0"))
       .andExpect(status().isOk());
  }
}