package jparepository;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertTrue;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.config.RootConfig;
import com.epam.dao.entity.NoteJpaEntity;
import com.epam.dao.entity.NotebookJpaEntity;
import com.epam.dao.entity.TagJpaEntity;
import com.epam.dao.entity.UserJpaEntity;
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


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
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
    assertThat(users.size(), is(6));
    assertThat(users, hasItem(dave));
    assertThat(usersCarters.size(), is(1));
    assertThat(usersCarters, hasItem(carter));
    assertThat(users, hasItem(carter));
  }

  @Test
  public void update_test() {
    UserJpaEntity dave = new UserJpaEntity(0, "Dave", "Mathews", Collections.emptySet(),
        Collections.emptySet(), Collections.emptySet());
    dave = userJpaRepository.save(dave);
    List<UserJpaEntity> users = userJpaRepository.findAll();
    assertThat(users, hasItem(dave));
    dave.setName("Dave The Second");
    userJpaRepository.save(dave);
    users = userJpaRepository.findAll();
    assertThat(users, hasItem(dave));
  }

  @Test
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
    notebook.setNotes(new HashSet<>(Collections.singleton(note)));
    dave.setNotes(new HashSet<>(Collections.singleton(note)));
    dave = userJpaRepository.save(dave);
    List<UserJpaEntity> users = userJpaRepository.findAll();
    assertThat(users, hasItem(dave));
    dave.setName("Dave The Second");
    userJpaRepository.save(dave);
    users = userJpaRepository.findAll();
    assertThat(users, hasItem(dave));
    assertThat(users.get(users.indexOf(dave))
                    .getTags()
                    .size(), is(1));
    assertThat(users.get(users.indexOf(dave))
                    .getNotebooks()
                    .size(), is(1));

    List<TagJpaEntity> tagsAll = tagJpaRepository.findAll();
    assertThat(tagsAll, hasItem(work));

    List<NotebookJpaEntity> notebooksAll = notebookJpaRepository.findAll();
    assertThat(notebooksAll, hasItem(notebook));
  }
}