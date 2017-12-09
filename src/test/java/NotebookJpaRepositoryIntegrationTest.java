import com.epam.config.ApplicationConfiguration;
import com.epam.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class NotebookJpaRepositoryIntegrationTest {

    @Autowired
    private NotebookJpaRepository repository;

    @Test
    public void save_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        NotebookJpaEntity first = new NotebookJpaEntity("first", dave);
        first = repository.save(first);

        NotebookJpaEntity second = new NotebookJpaEntity("second", dave);
        second = repository.save(second);

        List<NotebookJpaEntity> tags = repository.findAll();
        assertThat(tags.size(), is(2));
        assertThat(tags, hasItem(first));
        assertThat(tags, hasItem(second));
    }

    @Test
    public void update_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        NotebookJpaEntity first = new NotebookJpaEntity("first", dave);
        first = repository.save(first);

        List<NotebookJpaEntity> users = repository.findAll();
        assertThat(users, hasItem(first));
        first.setName("Second");
        repository.save(first);
        users = repository.findAll();
        assertThat(users, hasItem(first));
    }
}