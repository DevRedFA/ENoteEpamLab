import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


import java.util.List;

import com.epam.config.ApplicationConfiguration;
import com.epam.dao.JpaProxyUserRepository;
import com.epam.model.User;
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
public class JpaProxyUserRepositoryIntegrationTest {

    @Autowired
    private JpaProxyUserRepository repository;

    @Test
    public void sampleTestCase() {
        User dave = new User("Dave", "Mathews");
        dave = repository.save(dave);

        User carter = new User("Carter", "Beauford");
        carter = repository.save(carter);

        List<User> users = repository.all();
        assertThat(users.size(), is(2));
        assertThat(users, hasItem(dave));
        assertThat(users, hasItem(carter));
    }

}