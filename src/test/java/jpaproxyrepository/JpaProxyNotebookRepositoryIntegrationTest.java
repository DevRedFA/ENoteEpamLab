package jpaproxyrepository;

import com.epam.config.RootConfig;
import com.epam.dao.jpaproxyrepository.JpaProxyNotebookRepository;
import com.epam.dao.jpaproxyrepository.JpaProxyUserRepository;
import com.epam.models.Notebook;
import com.epam.models.User;
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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Transactional
public class JpaProxyNotebookRepositoryIntegrationTest {

    @Autowired
    private JpaProxyNotebookRepository notebookRepository;

    @Autowired
    private JpaProxyUserRepository userRepository;

    private Notebook spring;
    private Notebook university;
    private Notebook other;
    private int daveId;
    private int carterId;

    @Before
    public void init() {
        User dave = new User("Dave", "Mathews");
        dave = userRepository.save(dave);
        daveId = dave.getId();

        User carter = new User("Carter", "Beauford");
        carter = userRepository.save(carter);
        carterId = carter.getId();

        spring = new Notebook("Spring courses Epam", dave);
        spring = notebookRepository.save(spring);
        university = new Notebook("1st semester", dave);
        university = notebookRepository.save(university);
        other = new Notebook("other user's notebook", carter);
        notebookRepository.save(other);

    }

    @Test
    public void saveAndGetByIdTestCase() {
        List<Notebook> notebooks = notebookRepository.getByUserId(daveId);
        assertThat(notebooks.size(), is(2));
        assertThat(notebooks, hasItem(spring));
        assertThat(notebooks, hasItem(university));
    }

    @Test
    public void saveAndGetAllTestCase() {
        List<Notebook> notebooks = notebookRepository.all();
        assertThat(notebooks.size(), is(3));
        assertThat(notebooks, hasItem(spring));
        assertThat(notebooks, hasItem(university));
        assertThat(notebooks, hasItem(other));
    }

}