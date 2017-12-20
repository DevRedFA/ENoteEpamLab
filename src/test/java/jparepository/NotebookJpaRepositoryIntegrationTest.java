package jparepository;

import com.epam.config.RootConfig;
import com.epam.config.RootConfig;
import com.epam.dao.entity.NoteJpaEntity;
import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.dao.entity.TagJpaEntity;
import com.epam.dao.entity.UserJpaEntity;
import com.epam.dao.jparepository.NoteJpaRepository;
import com.epam.dao.jparepository.NotebookJpaRepository;
import com.epam.dao.jparepository.TagJpaRepository;
import com.epam.dao.jparepository.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
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
        assertThat(notebooks, hasItem(first));
        assertThat(notebooks, hasItem(second));
    }

    @Test
    public void update_test() {
        UserJpaEntity dave = new UserJpaEntity("Ivan", "Mathews");
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
        UserJpaEntity dave = new UserJpaEntity("Ilia", "Mathews");
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
        notebook.setNotes(new HashSet<>(Collections.singleton(note)));
        dave.setNotes(new HashSet<>(Collections.singleton(note)));

        dave = userJpaRepository.save(dave);
        notebook = notebookJpaRepository.save(notebook);
        List<NotebookJpaEntity> notebooksList = notebookJpaRepository.findAll();
        assertThat(notebooksList, contains(notebook));
        assertThat(notebooksList.get(0)
                .getNotes()
                .size(), is(1));
        assertThat(notebooksList.get(0)
                .getUser()
                .getId(), is(dave.getId()));
        assertThat(notebooksList.get(0)
                .getName(), is(notebook.getName()));

        List<TagJpaEntity> tagsAll = tagJpaRepository.findAll();
        assertThat(tagsAll, hasItem(work));
        List<NoteJpaEntity> notesAll = noteJpaRepository.findAll();
        assertThat(notesAll, hasItem(note));
        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users, hasItem(dave));
    }
}