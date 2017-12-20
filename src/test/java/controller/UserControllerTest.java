package controller;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.config.AspectJConfig;
import com.epam.config.RootConfig;
import com.epam.config.SwaggerConfig;
import com.epam.config.WebConfig;

import org.junit.Before;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class,
        SwaggerConfig.class, WebConfig.class, AspectJConfig.class})
@WebAppConfiguration
public class UserControllerTest {

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
    public void testGet() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse()
                .getContentAsString();
        String result = "{\"name\":\"Ivan1\",\"password\":\"Password\",\"tags\":[],\"notebooks\":[]}";
        assertEquals(result, contentAsString);
    }

    @Test
    public void testPut() throws Exception {
        String newUser = "{\"name\":\"Ivan 2\",\"password\":\"Password\",\"tags\":[],\"notebooks\":[],\"notes\":[]}";
        mvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newUser))
                .andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        String newUser = "{\"name\":\"Ivan 2\",\"password\":\"Password\",\"tags\":[],\"notebooks\":[],\"notes\":[]}";
        mvc.perform(post("/user/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newUser))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/user/0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAll() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/user/all"))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
    }
}