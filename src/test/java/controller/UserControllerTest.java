package controller;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.config.AspectJConfig;
import com.epam.config.RootConfig;
import com.epam.config.SwaggerConfig;
import com.epam.config.WebConfig;
import com.epam.dao.entity.UserJpaEntity;
import com.epam.mapper.UserMapper;
import com.epam.dto.UserDto;
import com.epam.models.Note;
import com.epam.models.Notebook;
import com.epam.models.Tag;
import com.epam.models.User;

import java.io.IOException;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    public void testConversion() throws IOException, InterruptedException {
        User user = new User("Ivan", "pas");
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tagsForUser = new HashSet<>(Arrays.asList(tag1, tag2));
        user.setTags(tagsForUser);

        Set<Tag> tagsForNote = new HashSet<>(Collections.singletonList(tag1));
        Notebook notebook = new Notebook("notebook", user);
        Set<Notebook> notebooks = new HashSet<>(Collections.singletonList(notebook));
        user.setNotebooks(notebooks);
        Note note = new Note("note1", "text", user, notebook);
        note.setTags(tagsForNote);
        note.setUser(user);
        Set<Note> notes = new HashSet<>(Collections.singletonList(note));
        notebook.setNotes(notes);
        user.setNotes(notes);

        UserJpaEntity userJpaEntity = UserMapper.toUserJpaEntity(user);
        User user1 = UserMapper.toUser(userJpaEntity);
        assertEquals(user, user1);

        UserDto userDto = UserMapper.toUserDto(user);
        User user2 = UserMapper.toUser(userDto);
        assertEquals(user, user2);
    }


    @Test
    public void testGet() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse()
                .getContentAsString();
        String result = "{\"name\":\"Ivan\",\"password\":\"Password\"}";
        assertEquals(result, contentAsString);
    }

    @Test
    public void testPut() throws Exception {
        User user = new User("Ivan", "pas");
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tagsForUser = new HashSet<>(Arrays.asList(tag1, tag2));
        user.setTags(tagsForUser);

        Set<Tag> tagsForNote = new HashSet<>(Collections.singletonList(tag1));
        Notebook notebook = new Notebook("notebook", user);
        Set<Notebook> notebooks = new HashSet<>(Collections.singletonList(notebook));
        user.setNotebooks(notebooks);
        Note note = new Note("note1", "text", user, notebook);
        note.setTags(tagsForNote);
        note.setUser(user);
        Set<Note> notes = new HashSet<>(Collections.singletonList(note));
        notebook.setNotes(notes);
        user.setNotes(notes);

        MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
        String s = UserMapper.toUserDto(user)
                .toString();
        String userst = "{\n"
                + "  \"firstName\": \"RealName\",\n"
                + "  \"lastName\": \"RealLastName\",\n"
                + "  \"login\": \"realLogin\",\n"
                + "  \"password\": \"12345\",\n"
                + "  \"birthday\": null,\n"
                + "  \"noteBooks\": []\n"
                + "}";
        String newUser = "{\"name\":\"Ivan 2\",\"password\":\"Password\",\"tags\":[],\"notebooks\":[],\"notes\":[]}";
//        String UserSting = UserMapper.toUserDtoString(user);
        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newUser))
                .andExpect(status().isOk());
    }
}