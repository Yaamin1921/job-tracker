package com.jobtracker.controller;

import com.jobtracker.dto.NoteRequest;
import com.jobtracker.dto.NotesDto;
import com.jobtracker.entity.Note;
import com.jobtracker.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    @Autowired
    private final NoteService noteService;

    @PostMapping("/{jobId}")
    public ResponseEntity<Note> addNote(
            @PathVariable Long jobId,
            @RequestBody NoteRequest request) {

        return ResponseEntity.ok(
                noteService.addNote(jobId, request.getContent(), request.getType())
        );
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<List<NotesDto>> getNotes(@PathVariable Long jobId) {
        return ResponseEntity.ok(noteService.getNotes(jobId));
    }
}