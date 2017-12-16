package jpaproxyrepository;

import com.epam.config.RootConfig;
import com.epam.dao.jpaproxyrepository.JpaProxyTagRepository;
import com.epam.models.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class JpaProxyTagRepositoryIntegrationTest {

    @Autowired
    private JpaProxyTagRepository repository;

    @Test
    public void saveToRepositoryTestCase() {
        Tag work = new Tag("work");
        work = repository.save(work);

        Tag study = new Tag("study");
        study = repository.save(study);

        List<Tag> tags = repository.all();
        assertThat(tags.size(), is(2));
        assertThat(tags, hasItem(work));
        assertThat(tags, hasItem(study));
    }

}