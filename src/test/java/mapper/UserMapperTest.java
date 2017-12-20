package mapper;

import com.epam.dao.entity.UserJpaEntity;
import com.epam.dto.UserDto;
import com.epam.mapper.UserMapper;
import com.epam.service.models.Note;
import com.epam.service.models.Notebook;
import com.epam.service.models.Tag;
import com.epam.service.models.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {

    @Test
    public void testConversion() {
        User user = new User("Ivan", "pas");
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tagsForUser = new HashSet<>(Arrays.asList(tag1, tag2));
        user.setTags(tagsForUser);

        Set<Tag> tagsForNote = new HashSet<>(Collections.singletonList(tag1));
        Notebook notebook = new Notebook("notebook", user);
        Set<Notebook> notebooks = new HashSet<>(Collections.singletonList(notebook));
        user.setNotebooks(notebooks);
        Note note = new Note("note1", "text", user, notebook);
        note.setTags(tagsForNote);
        note.setUser(user);
        Set<Note> notes = new HashSet<>(Collections.singletonList(note));
        notebook.setNotes(notes);
        user.setNotes(notes);

        UserJpaEntity userJpaEntity = UserMapper.toUserJpaEntity(user);
        User user1 = UserMapper.toUser(userJpaEntity);
        assertEquals(user, user1);

        UserDto userDto = UserMapper.toUserDto(user);
        User user2 = UserMapper.toUser(userDto);
        assertEquals(user, user2);
    }
}
