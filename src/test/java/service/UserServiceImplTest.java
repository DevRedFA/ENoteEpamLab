package service;

import com.epam.dao.jpaproxyrepository.JpaProxyUserRepository;
import com.epam.models.User;
import com.epam.services.implementations.UserServiceImpl;
import com.epam.services.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private JpaProxyUserRepository jpaProxyUserRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Before
    public void init() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("alina", "qwerty"));
        userList.add(new User("alina_golovanova", "password"));
        userList.add(new User("alinag", "123456"));

        User user = new User("alina", "123");

        when(jpaProxyUserRepository.all()).thenReturn(userList);
        when(jpaProxyUserRepository.getByName("alina")).thenReturn(user);
    }

    @Test
    public void allTest() {
        List<User> users = userService.all();
        assertEquals(3, users.size());
    }

    @Test
    public void getByNameTest() {
        User user = userService.getByName("alina");
        assertEquals("alina", user.getName());
        assertEquals("123", user.getPassword());
    }

}
