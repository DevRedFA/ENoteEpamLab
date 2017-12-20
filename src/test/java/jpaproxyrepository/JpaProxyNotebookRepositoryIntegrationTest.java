package jpaproxyrepository;

import com.epam.config.RootConfig;
import com.epam.service.models.Notebook;
import com.epam.service.models.NotebookRepository;
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

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class JpaProxyNotebookRepositoryIntegrationTest {

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private UserRepository userRepository;

    private Notebook spring;
    private Notebook university;
    private Notebook other;
    private long daveId;
    private long carterId;
    private long springId;
    private User dave;

    @Before
    public void init() {
        dave = new User("Dave", "Mathews");
        dave = userRepository.save(dave);
        daveId = dave.getId();

        User carter = new User("Carter", "Beauford");
        carter = userRepository.save(carter);
        carterId = carter.getId();

        spring = new Notebook("Spring courses Epam", dave);
        spring = notebookRepository.save(spring);
        springId = spring.getId();
        university = new Notebook("1st semester", dave);
        university = notebookRepository.save(university);
        other = new Notebook("other user's notebook", carter);
        notebookRepository.save(other);

    }

    @Test
    public void getByUserIdTest() {
        List<Notebook> notebooks = notebookRepository.findByUserId(daveId);
        assertThat(notebooks.size(), is(2));
        assertThat(notebooks, hasItem(spring));
        assertThat(notebooks, hasItem(university));
    }

    @Test
    public void getByIdTest() {
        Notebook notebook = notebookRepository.getById(springId);
        assertThat(notebook, is(spring));
        assertThat(notebook.getName(), is("Spring courses Epam"));
        assertThat(notebook.getUser(), is(dave));
    }

    @Test
    public void getAllTest() {
        List<Notebook> notebooks = notebookRepository.all();
        assertThat(notebooks.size(), is(3));
        assertThat(notebooks, hasItem(spring));
        assertThat(notebooks, hasItem(university));
        assertThat(notebooks, hasItem(other));
    }

    @Test
    public void updateTestCase() {
        spring.setName("new spring");
        notebookRepository.update(spring);
        Notebook notebook = notebookRepository.getById(springId);
        assertThat(notebook, is(spring));
        assertThat(notebook.getName(), is("new spring"));
    }

    @Test
    public void deleteTest() {
        notebookRepository.delete(spring);
        List<Notebook> notebooks = notebookRepository.findByUserId(daveId);
        assertTrue(!notebooks.contains(spring));
    }

}