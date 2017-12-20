package jpaproxyrepository;

import com.epam.config.RootConfig;
import com.epam.service.models.Tag;
import com.epam.service.models.TagRepository;
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
public class JpaProxyTagRepositoryIntegrationTest {

    @Autowired
    private TagRepository repository;

    private long workId;
    private Tag work;
    private Tag study;

    @Before
    public void init() {
        work = new Tag("work");
        work = repository.save(work);
        workId = work.getId();

        study = new Tag("study");
        study = repository.save(study);
    }

    @Test
    public void getAllTest() {
        List<Tag> tags = repository.all();
        assertThat(tags.size(), is(2));
        assertThat(tags, hasItem(work));
        assertThat(tags, hasItem(study));
    }

    @Test
    public void getByIdTest() {
        Tag tag = repository.getById(workId);
        assertThat(tag, is(work));
        assertThat(tag.getName(), is("work"));
    }

    @Test
    public void updateTest() {
        work.setName("new work");
        repository.update(work);
        Tag tag = repository.getById(workId);
        assertThat(tag, is(work));
        assertThat(tag.getName(), is("new work"));
    }
    @Test
    public void deleteTest() {
        repository.delete(workId);
        List<Tag> tags = repository.all();
        assertTrue(!tags.contains(work));
    }


}