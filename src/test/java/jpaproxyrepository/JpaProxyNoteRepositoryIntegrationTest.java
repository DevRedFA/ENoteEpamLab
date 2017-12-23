package jpaproxyrepository;

import com.epam.config.RootConfig;
import com.epam.service.models.*;
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
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
public class JpaProxyNoteRepositoryIntegrationTest {

  @Autowired
  private NotebookRepository notebookRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private NoteRepository noteRepository;


  private Note note;
  private long noteId;
  private long daveId;

  @Before
  public void init() {
    User dave = new User("Dave", "Mathews");
    dave = userRepository.save(dave);
    daveId = dave.getId();

    Notebook spring = new Notebook("Spring courses Epam", dave);
    spring = notebookRepository.save(spring);

    note = new Note("note_name", "note", dave, spring);
    note = noteRepository.save(note);
    noteId = note.getId();
  }


  @Test
  public void getAllTest() {
    List<Note> notes = noteRepository.all();
    assertThat(notes, hasItem(note));
  }


  @Test
  public void getByIdTest() {
    Note testNote = noteRepository.getById(noteId);
    assertThat(testNote, is(note));
  }


  @Test
  public void getByUserIdTest() {
    List<Note> notes = noteRepository.getByUserId(daveId);
    assertThat(notes, hasItem(note));
    assertThat(notes.size(), is(1));
  }


  @Test
  public void updateTest() {
    note.setName("new note");
    Note testNote = noteRepository.getById(noteId);
    noteRepository.update(note);
    testNote = noteRepository.getById(noteId);
    assertThat(testNote, is(note));
    assertThat(testNote.getName(), is("new note"));
  }


  @Test
  public void deleteTest() {
    noteRepository.delete(noteId);
    List<Note> notes = noteRepository.getByUserId(daveId);
    assertTrue(notes.isEmpty());
  }

}