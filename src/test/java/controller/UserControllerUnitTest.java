package controller;

import com.epam.controller.UserController;
import com.epam.dto.UserDto;
import com.epam.dto.interfaces.UserDtoService;
import com.epam.mapper.UserMapper;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserControllerUnitTest {

    @Mock
    private UserDtoService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void testGet() throws Exception {
        User user = new User(1, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        when(userService.getById(1)).thenReturn(UserMapper.toUserDto(user));
        mvc.perform(get("/user/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).getById(user.getId());
    }

    @Test
    public void testPut() throws Exception {
        User user = new User(0, "Ivan 2", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        String newUser = "{\"name\":\"Ivan 2\",\"password\":\"password\",\"tags\":[],\"notebooks\":[],\"notes\":[]}";
        mvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newUser))
                .andExpect(status().isOk());
        verify(userService, times(1)).save(UserMapper.toUserDto(user));
    }

    @Test
    public void testPost() throws Exception {
        User user = new User(0, "Ivan 2", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        String newUser = "{\"name\":\"Ivan 2\",\"password\":\"password\",\"tags\":[],\"notebooks\":[],\"notes\":[]}";
        mvc.perform(post("/user/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(newUser))
                .andExpect(status().isOk());
        verify(userService, times(1)).update(0, UserMapper.toUserDto(user));
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User(1L, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        when(userService.getById(1L)).thenReturn(UserMapper.toUserDto(user));
        when(userService.delete(1L)).thenReturn(true);
        mvc.perform(delete("/user/1"))
                .andExpect(status().isOk());
        verify(userService, times(1)).delete(user.getId());
    }

    @Test
    public void testAll() throws Exception {
        User user = new User(1L, "Ivan1", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        when(userService.getById(1L)).thenReturn(UserMapper.toUserDto(user));
        User user1 = new User(2L, "Ivan2", "password", new HashSet<Tag>(), new HashSet<Notebook>(), new HashSet<Note>());
        when(userService.getById(1L)).thenReturn(UserMapper.toUserDto(user));
        List<UserDto> users = Arrays.asList(UserMapper.toUserDto(user), UserMapper.toUserDto(user1));
        when(userService.all()).thenReturn(users);
        mvc.perform(get("/user/all"))
                .andExpect(status().isOk());
        verify(userService, times(1)).all();
    }
}

