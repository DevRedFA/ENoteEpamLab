package com.epam.services.implementations;

import com.epam.models.Note;
import com.epam.models.NoteRepository;
import com.epam.models.Notebook;
import com.epam.models.Tag;
import com.epam.services.interfaces.NoteService;
import com.epam.services.interfaces.NotebookService;
import com.epam.services.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository jpaProxyNoteRepository;

    @Autowired
    TagService tagService;

    @Autowired
    private NotebookService notebookService;

    public Note save(Note note) {
        return jpaProxyNoteRepository.save(note);
    }

    @Override
    public Note save(long notebookId, Note note) {
        Notebook notebook = notebookService.getById(notebookId);
        notebook.getNotes().add(note);
        notebookService.update(notebook);
        //TODO: something strange, need to think
        Note resultNote = null;
        List<Note> notes = this.getByNotebookId(notebookId);
        for (Note note1 : notes) {
            if (note1.equals(note)) {
                resultNote = note1;
            }
        }
        return resultNote;
    }

    public List<Note> all() {
        return jpaProxyNoteRepository.all();
    }

    public List<Note> getByUserId(long userId) {
        return jpaProxyNoteRepository.getByUserId(userId);
    }

    public Note getById(long id) {
        return jpaProxyNoteRepository.getById(id);
    }

    public void update(Note note) {
        jpaProxyNoteRepository.update(note);
    }

    @Override
    public void update(long noteId, Note note) {
        Note oldNote = this.getById(noteId);
        //TODO: check fields for null
        oldNote.setCreated(note.getCreated());
        oldNote.setName(note.getName());
        oldNote.setNotebook(note.getNotebook());
        oldNote.setTags(note.getTags());
        oldNote.setUser(note.getUser());
        oldNote.setUpdated(note.getUpdated());
        oldNote.setText(note.getText());
        this.update(note);
    }

    @Override
    public void delete(long noteId) {
        jpaProxyNoteRepository.delete(noteId);
    }

    @Override
    public List<Note> getByUserIdAndTagId(long userId, long tagId) {
        List<Note> resultNotes = new ArrayList<>();
        Tag tag = tagService.getById(tagId);
        List<Note> notes = jpaProxyNoteRepository.getByUserId(userId);
        for (Note note : notes) {
            if (note.getTags().contains(tag)) {
                resultNotes.add(note);
            }
        }
        return resultNotes;
    }

    @Override
    public List<Note> getByNotebookId(long notebookId) {
        Notebook notebook = notebookService.getById(notebookId);
        return new ArrayList<>(notebook.getNotes());
    }

    @Override
    public List<Note> getByNotebookIdAndTagId(long notebookId, long tagId) {
        List<Note> result = new ArrayList<>();
        Notebook notebook = notebookService.getById(notebookId);
        Set<Note> notes = notebook.getNotes();
        Tag tagToFilter = tagService.getById(tagId);
        for (Note note : notes) {
            if (note.getTags().contains(tagToFilter)) {
                result.add(note);
            }
        }
        return result;
    }
}
