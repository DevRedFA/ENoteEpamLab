package service;

import com.epam.dao.jpaproxyrepository.JpaProxyNoteRepository;
import com.epam.service.models.Note;
import com.epam.service.models.Notebook;
import com.epam.service.models.User;
import com.epam.service.implementations.NoteServiceImpl;
import com.epam.service.interfaces.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplTest {

    @Mock
    private JpaProxyNoteRepository jpaProxyNoteRepository;

    @InjectMocks
    private NoteService noteService = new NoteServiceImpl();

    private Note newNote;

    @Before
    public void init() {
        User user = new User("alina", "123");
        Notebook notebook = new Notebook("notebook", user);

        Note note = new Note("note", "text", user, notebook);
        List<Note> notes1 = new ArrayList<>();
        notes1.add(note);
        notes1.add(new Note("note1", "text1", user, notebook));
        List<Note> notes = new ArrayList<>(notes1);
        notes.add(new Note("note2", "text2", new User(), new Notebook()));
        newNote = new Note("note3", "text3", user, notebook);

        when(jpaProxyNoteRepository.all()).thenReturn(notes);
        when(jpaProxyNoteRepository.getById(1)).thenReturn(note);
        when(jpaProxyNoteRepository.getByUserId(1)).thenReturn(notes1);
        when(jpaProxyNoteRepository.save(newNote)).thenReturn(newNote);
    }

    @Test
    public void allTest() {
        List<Note> notes = noteService.all();
        assertEquals(3, notes.size());
    }

    @Test
    public void getByUserIdTest() {
        List<Note> notes = noteService.getByUserId(1);
        assertEquals(2, notes.size());
    }

    @Test
    public void getByIdTest() {
        Note note = noteService.getById(1);
        assertEquals("note", note.getName());
        assertEquals("text", note.getText());
        assertEquals("notebook", note.getNotebook().getName());
        assertEquals("alina", note.getUser().getName());
    }

    @Test
    public void saveTest() {
        Note note = noteService.save(newNote);
        assertEquals(newNote, note);
    }
}
