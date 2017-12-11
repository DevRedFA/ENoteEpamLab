import com.epam.config.ApplicationConfiguration;
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
public class TagJpaRepositoryIntegrationTest {

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
        TagJpaEntity work = new TagJpaEntity("Work");
        work = tagJpaRepository.save(work);

        TagJpaEntity home = new TagJpaEntity("Home");
        home = tagJpaRepository.save(home);

        List<TagJpaEntity> tags = tagJpaRepository.findAll();
        assertThat(tags.size(), is(2));
        assertThat(tags, hasItem(home));
        assertThat(tags, hasItem(work));
    }

    @Test
    public void update_test() {
        TagJpaEntity work = new TagJpaEntity("Work");
        work = tagJpaRepository.save(work);
        List<TagJpaEntity> users = tagJpaRepository.findAll();
        assertThat(users, hasItem(work));
        work.setName("Second Tag");
        tagJpaRepository.save(work);
        users = tagJpaRepository.findAll();
        assertThat(users, hasItem(work));
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

        userJpaRepository.save(dave);

        work = tagJpaRepository.save(work);
        List<TagJpaEntity> tagsList = tagJpaRepository.findAll();
        assertThat(tagsList, contains(work));
        assertThat(tagsList.size(), is(1));
        assertThat(tagsList.get(0).getId(), is(work.getId()));
        assertThat(tagsList.get(0).getName(), is("Work"));
        assertThat(tagsList.get(0), is(work));

        List<NoteJpaEntity> notesAll = noteJpaRepository.findAll();
        assertThat(notesAll.size(), is(1));
        List<UserJpaEntity> users = userJpaRepository.findAll();
        assertThat(users.size(), is(1));
    }
}