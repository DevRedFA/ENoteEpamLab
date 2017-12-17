package service;

import com.epam.dao.jpaproxyrepository.JpaProxyTagRepository;
import com.epam.models.Tag;
import com.epam.services.implementations.TagServiceImpl;
import com.epam.services.interfaces.TagService;
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
public class TagServiceImplTest {

    @Mock
    private JpaProxyTagRepository jpaProxyTagRepository;

    @InjectMocks
    private TagService tagService = new TagServiceImpl();

    @Before
    public void init() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("work"));
        tags.add(new Tag("study"));
        tags.add(new Tag("university"));
        tags.add(new Tag("spring"));

        when(jpaProxyTagRepository.all()).thenReturn(tags);
    }

    @Test
    public void allTest() {
        List<Tag> tags = tagService.all();
        assertEquals(4, tags.size());
    }



}
