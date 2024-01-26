package com.example.NotesRest.services;

import com.example.NotesRest.models.Note;
import com.example.NotesRest.repository.NoteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getById(long id) {
        Note note;
        try {
            note = noteRepository.findById(id).orElseThrow(null);
            return note;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Note create(Note note) {
        return noteRepository.save(note);
    }

    public Note update(Note note) {
        Note updNote = getById(note.getId());
        updNote.setTitle(note.getTitle());
        updNote.setDescription(note.getDescription());
        return noteRepository.save(updNote);
    }

    public void delete(long id) {
        noteRepository.deleteById(id);
    }

}
