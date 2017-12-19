package controller;


import static org.junit.Assert.assertEquals;

import com.epam.dao.mapper.UserMapper;
import com.epam.dto.NoteDto;
import com.epam.dto.NotebookDto;
import com.epam.dto.TagDto;
import com.epam.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest {

    @Test
    public void testConversion() throws IOException {
    }
}
