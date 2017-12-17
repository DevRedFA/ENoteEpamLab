package service;

import com.epam.dao.jpaproxyrepository.JpaProxyNotebookRepository;
import com.epam.models.Notebook;
import com.epam.models.User;
import com.epam.services.implementations.NotebookServiceImpl;
import com.epam.services.interfaces.NotebookService;
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
public class NotebookServiceImplTest {

    @Mock
    private JpaProxyNotebookRepository jpaProxyNotebookRepository;

    @InjectMocks
    private NotebookService notebookService = new NotebookServiceImpl();

    @Before
    public void init() {
        User alina = new User("alina", "123");
        Notebook notebook1 = new Notebook("notebook1", alina);
        Notebook notebook2 = new Notebook("notebook2", alina);
        Notebook notebook3 = new Notebook("notebook3", new User());

        List<Notebook> notebooks1 = new ArrayList<>();
        notebooks1.add(notebook1);
        notebooks1.add(notebook2);

        List<Notebook> notebooks = new ArrayList<>(notebooks1);
        notebooks.add(notebook3);


        when(jpaProxyNotebookRepository.all()).thenReturn(notebooks);
        when(jpaProxyNotebookRepository.getByUserId(1)).thenReturn(notebooks1);
    }

    @Test
    public void allTest() {
        List<Notebook> notebooks = notebookService.all();
        assertEquals(3, notebooks.size());
    }

    @Test
    public void getByUserId() {
        List<Notebook> notebooks = notebookService.getByUserId(1);
        assertEquals(2, notebooks.size());
    }

}
