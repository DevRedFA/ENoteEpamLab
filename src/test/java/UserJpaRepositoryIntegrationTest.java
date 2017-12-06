

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.config.ApplicationConfiguration;
import com.epam.dao.TagJpaEntity;
import com.epam.dao.TagJpaRepository;
import com.epam.dao.UserJpaEntity;
import com.epam.dao.UserJpaRepository;
import com.epam.model.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class UserJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private TagJpaRepository tagJpaRepository;

    @Test
    public void save_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        dave = userJpaRepository.save(dave);

        UserJpaEntity carter = new UserJpaEntity("Carter", "Beauford");
        carter = userJpaRepository.save(carter);

        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users.size(), is(2));
        assertThat(users, hasItem(dave));
        assertThat(users, hasItem(carter));
    }

    @Test
    public void update_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        dave = userJpaRepository.save(dave);
        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users, contains(dave));
        dave.setName("Dave The Second");
        userJpaRepository.save(dave);
        users = userJpaRepository.findAll();
        assertThat(users, hasItem(dave));
    }

    @Test
    public void create_with_tag_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        Set<TagJpaEntity> tags = new HashSet<TagJpaEntity>();
        TagJpaEntity work = new TagJpaEntity("Work");
        tags.add(work);
        dave.setTags(tags);
        dave = userJpaRepository.save(dave);
        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users, contains(dave));
        dave.setName("Dave The Second");
        userJpaRepository.save(dave);
        users = userJpaRepository.findAll();
        assertThat(users, hasItem(dave));
        List<TagJpaEntity> tagsAll = tagJpaRepository.findAll();
        assertThat(tagsAll.size(), is(1));
    }
}