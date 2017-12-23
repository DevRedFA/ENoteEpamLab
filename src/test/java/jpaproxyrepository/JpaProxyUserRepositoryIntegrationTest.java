package jpaproxyrepository;

import com.epam.config.RootConfig;
import com.epam.service.models.User;
import com.epam.service.models.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class JpaProxyUserRepositoryIntegrationTest {

    @Autowired
    private UserRepository repository;

    private User dave;
    private User carter;
    private long daveId;

    @Before
    public void init() {
        dave = new User("Dave", "Mathews");
        dave = repository.save(dave);

        carter = new User("Carter", "Beauford");
        carter = repository.save(carter);

        daveId = dave.getId();

    }

    @Test
    @Transactional
    public void sampleTestCase() {
        List<User> users = repository.all();
        assertThat(users.size(), is(6));
        assertThat(users, hasItem(dave));
        assertThat(users, hasItem(carter));
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        repository.deleteById(daveId);
        List<User> users = repository.all();
        assertTrue(!users.contains(dave));
        assertTrue(users.contains(carter));
    }

    @Test
    @Transactional
    public void deleteTest() {
        repository.delete(dave);
        List<User> users = repository.all();
        assertTrue(!users.contains(dave));
        assertTrue(users.contains(carter));
    }

    @Test
    @Transactional
    public void getByIdTest() {
        User user = repository.getById(daveId);
        assertThat(user.getName(), is("Dave"));
        assertThat(user, is(dave));
    }

    @Test
    @Transactional
    public void getByIdExpectedNullTest() {
        User user = repository.getById(404);
        assertNull(user);
    }

    @Test
    @Transactional
    public void getByNameTest() {
        User user = repository.getByName("Dave");
        assertThat(user.getName(), is("Dave"));
        assertThat(user, is(dave));
    }

    @Test
    @Transactional
    public void getByNameExpectedNullTest() {
        User user = repository.getByName("Alina");
        assertNull(user);
    }

    @Test
    @Transactional
    public void updateTest() {
        User newUser = new User("new Dave", "password");
        newUser = repository.update(daveId, newUser);
        assertThat(newUser.getName(), is("new Dave"));
    }

    @Test
    @Transactional
    public void updateUserTest() {
        User newUser = new User(daveId, "new Dave", "password", null, null, null);
        repository.update(newUser);
        User user = repository.getById(daveId);

        assertThat(user.getName(), is("new Dave"));
        assertThat(user, is(newUser));
    }

    @Test
    @Transactional
    public void updateNullUserTest() {
        User newUser = new User("new Dave", "password");
        repository.update(404, newUser);
        User user = repository.getById(404);
        assertNull(user);
    }


}