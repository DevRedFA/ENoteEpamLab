import com.epam.config.ApplicationConfiguration;
import com.epam.dao.jpaproxyrepository.JpaProxyNoteRepository;
import com.epam.dao.jpaproxyrepository.JpaProxyNotebookRepository;
import com.epam.dao.jpaproxyrepository.JpaProxyTagRepository;
import com.epam.dao.jpaproxyrepository.JpaProxyUserRepository;
import com.epam.models.Note;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Transactional
public class JpaProxyNoteRepositoryIntegrationTest {

    @Autowired
    private JpaProxyNotebookRepository notebookRepository;

    @Autowired
    private JpaProxyUserRepository userRepository;

    @Autowired
    private JpaProxyNoteRepository noteRepository;

    @Autowired
    private JpaProxyTagRepository tagRepository;

    Note note;

    @Before
    public void init() {
        User dave = new User("Dave", "Mathews");
        dave = userRepository.save(dave);
        int daveId = dave.getId();

        Notebook spring = new Notebook("Spring courses Epam", dave);
        spring = notebookRepository.save(spring);
        int springId = spring.getId();

        note = new Note("note_name", "note", dave, spring);
        noteRepository.save(note);
    }

    @Test
    public void sampleTestCase() {
        List<Note> notes = noteRepository.all();
        assertThat(notes, hasItem(note));
        assertThat(notes.size(), is(1));
    }

}