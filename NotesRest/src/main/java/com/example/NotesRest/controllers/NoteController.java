package com.example.NotesRest.controllers;

import com.example.NotesRest.models.Note;
import com.example.NotesRest.services.NoteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        return new ResponseEntity<>(noteService.create(note), HttpStatus.CREATED);
    }
}
