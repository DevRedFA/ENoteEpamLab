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
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class NoteJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagJpaRepository tagJpaRepository;

    @Autowired
    private NoteJpaRepository noteJpaRepository;

    @Autowired
    private NotebookJpaRepository notebookJpaRepository;



    @Test
    @Transactional
    public void create_with_tag_and_notebook_and_user_test() {
        UserJpaEntity dave = new UserJpaEntity("Dave", "Mathews");
        Set<TagJpaEntity> tags = new HashSet<TagJpaEntity>();
        TagJpaEntity work = new TagJpaEntity("Work");
        tags.add(work);
        dave.setTags(tags);

        Set<NotebookJpaEntity> notebooks = new HashSet<NotebookJpaEntity>();
        NotebookJpaEntity notebook = new NotebookJpaEntity("First Notebook", dave);
        notebooks.add(notebook);
        dave.setNotebooks(notebooks);
        dave = userJpaRepository.save(dave);

        NoteJpaEntity note = new NoteJpaEntity("Note 1", "text", dave, notebook);
        note.setTags(tags);
        note = noteJpaRepository.save(note);
        notebook.setNotes(new HashSet<NoteJpaEntity>(Collections.singleton(note)));
        notebookJpaRepository.save(notebook);
        dave.setNotes(new HashSet<NoteJpaEntity>(Collections.singleton(note)));

        List<NoteJpaEntity> notes = noteJpaRepository.findAll();
        assertThat(notes, contains(note));

        note.setName("note 2");
        noteJpaRepository.save(note);
        notes = noteJpaRepository.findAll();
        assertThat(notes, contains(note));

        assertThat(notes.get(0).getTags(), is(tags));
        assertThat(notes.get(0).getUser(), is(dave));

        List<TagJpaEntity> tagsAll = tagJpaRepository.findAll();
        assertThat(tagsAll.size(), is(1));

        List<NotebookJpaEntity> notebooksAll = notebookJpaRepository.findAll();
        assertThat(notebooksAll.size(), is(1));
    }
}