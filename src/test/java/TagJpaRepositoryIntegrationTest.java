import com.epam.config.ApplicationConfiguration;
import com.epam.dao.TagJpaEntity;
import com.epam.dao.TagJpaRepository;
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
public class TagJpaRepositoryIntegrationTest {

    @Autowired
    private TagJpaRepository repository;

    @Test
    public void save_test() {
        TagJpaEntity work = new TagJpaEntity("Work");
        work = repository.save(work);

        TagJpaEntity home = new TagJpaEntity("Home");
        home = repository.save(home);

        List<TagJpaEntity> tags = repository.findAll();
        assertThat(tags.size(), is(2));
        assertThat(tags, hasItem(home));
        assertThat(tags, hasItem(work));
    }

    @Test
    public void update_test() {
        TagJpaEntity work = new TagJpaEntity("Work");
        work = repository.save(work);
        List<TagJpaEntity> users = repository.findAll();
        assertThat(users, hasItem(work));
        work.setName("Dave The Second");
        repository.save(work);
        users = repository.findAll();
        assertThat(users, hasItem(work));
    }
}