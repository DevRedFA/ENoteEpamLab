import com.epam.config.ApplicationConfiguration;
import com.epam.dao.*;
import com.epam.model.Notebook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class NotebookJpaRepositoryIntegrationTest {


    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagJpaRepository tagJpaRepository;

    @Autowired
    private NotebookJpaRepository notebookJpaRepository;

    @Autowired
    private NoteJpaRepository noteJpaRepository;


    @Test
    public void save_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        userJpaRepository.save(dave);
        NotebookJpaEntity first = new NotebookJpaEntity("first", dave);
        first = notebookJpaRepository.save(first);

        NotebookJpaEntity second = new NotebookJpaEntity("second", dave);
        second = notebookJpaRepository.save(second);

        List<NotebookJpaEntity> notebooks = notebookJpaRepository.findAll();
        assertThat(notebooks.size(), is(2));
        assertThat(notebooks, hasItem(first));
        assertThat(notebooks, hasItem(second));
    }

    @Test
    public void update_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        userJpaRepository.save(dave);
        NotebookJpaEntity first = new NotebookJpaEntity("first", dave);
        first = notebookJpaRepository.save(first);

        List<NotebookJpaEntity> notebooks = notebookJpaRepository.findAll();
        assertThat(notebooks, hasItem(first));
        first.setName("Second");
        notebookJpaRepository.save(first);
        notebooks = notebookJpaRepository.findAll();
        assertThat(notebooks, hasItem(first));
    }

    @Test
    @Transactional
    public void createWithNoteAndTagAndUser() {
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
        notebook = notebookJpaRepository.save(notebook);
        List<NotebookJpaEntity> notebooksList = notebookJpaRepository.findAll();
        assertThat(notebooksList, contains(notebook));
        assertThat(notebooksList.size(), is(1));
        assertThat(notebooksList.get(0).getNotes().size(), is(1));
        assertThat(notebooksList.get(0).getUser().getId(), is(dave.getId()));
        assertThat(notebooksList.get(0).getName(), is(notebook.getName()));
        Notebook nb = notebooksList.get(0).toNotebook();
        assertThat(nb, is(notebook.toNotebook()));

        List<TagJpaEntity> tagsAll = tagJpaRepository.findAll();
        assertThat(tagsAll.size(), is(1));
        List<NoteJpaEntity> notesAll = noteJpaRepository.findAll();
        assertThat(notesAll.size(), is(1));
        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users.size(), is(1));
    }
}