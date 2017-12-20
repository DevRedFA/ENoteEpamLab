package service;

import com.epam.dao.jpaproxyrepository.JpaProxyTagRepository;
import com.epam.service.models.Tag;
import com.epam.service.implementations.TagServiceImpl;
import com.epam.service.interfaces.TagService;
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

    private Tag newTag;

    @Before
    public void init() {
        List<Tag> tags = new ArrayList<>();
        Tag work = new Tag("work");
        tags.add(work);
        tags.add(new Tag("study"));
        tags.add(new Tag("university"));
        tags.add(new Tag("spring"));
        newTag = new Tag("newTag");

        when(jpaProxyTagRepository.all()).thenReturn(tags);
        when(jpaProxyTagRepository.getById(1)).thenReturn(work);
        when(jpaProxyTagRepository.save(newTag)).thenReturn(newTag);
    }

    @Test
    public void allTest() {
        List<Tag> tags = tagService.all();
        assertEquals(4, tags.size());
    }

    @Test
    public void getByIdTest() {
        Tag tag = tagService.getById(1);
        assertEquals(tag.getName(), "work");
    }

    @Test
    public void saveTest() {
        Tag tag = tagService.save(newTag);
        assertEquals(tag.getName(), "newTag");
        assertEquals(newTag, tag);
    }



}
