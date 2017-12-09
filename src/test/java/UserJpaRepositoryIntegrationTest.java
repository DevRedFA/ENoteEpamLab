

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.config.ApplicationConfiguration;
import com.epam.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class UserJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagJpaRepository tagJpaRepository;

    @Autowired
    private NotebookJpaRepository notebookJpaRepository;

    @Test
    public void save_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        dave = userJpaRepository.save(dave);

        UserJpaEntity carter = new UserJpaEntity("Carter", "Beauford");
        carter = userJpaRepository.save(carter);

        List<UserJpaEntity> users = userJpaRepository.findAll();

        List<UserJpaEntity> usersCarters = userJpaRepository.findAllByName("Carter");
        assertThat(users.size(), is(2));
        assertThat(users, hasItem(dave));
        assertThat(usersCarters.size(), is(1));
        assertThat(usersCarters, hasItem(carter));
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
    @Transactional
    public void create_with_tag_and_notebook_and_note_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        Set<TagJpaEntity> tags = new HashSet<TagJpaEntity>();
        TagJpaEntity work = new TagJpaEntity("Work");
        tags.add(work);
        dave.setTags(tags);

        Set<NotebookJpaEntity> notebooks = new HashSet<NotebookJpaEntity>();
        NotebookJpaEntity notebook = new NotebookJpaEntity("First Notebook", dave);
        notebooks.add(notebook);
        dave.setNotebooks(notebooks);

        NoteJpaEntity note = new NoteJpaEntity("Note 1", "text", dave, notebook);
        note.setTags(tags);
        notebook.setNotes(new HashSet<NoteJpaEntity>(Collections.singleton(note)));
        dave.setNotes(new HashSet<NoteJpaEntity>(Collections.singleton(note)));
        dave = userJpaRepository.save(dave);
        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users, contains(dave));
        dave.setName("Dave The Second");
        userJpaRepository.save(dave);
        users = userJpaRepository.findAll();
        assertThat(users, hasItem(dave));
        assertThat(users.get(0).getTags().size(), is(1));
        assertThat(users.get(0).getNotebooks().size(), is(1));

        List<TagJpaEntity> tagsAll = tagJpaRepository.findAll();
        assertThat(tagsAll.size(), is(1));

        List<NotebookJpaEntity> notebooksAll = notebookJpaRepository.findAll();
        assertThat(notebooksAll.size(), is(1));
    }
}