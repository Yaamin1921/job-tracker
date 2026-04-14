package com.jobtracker.service;

import com.jobtracker.entity.Note;

import java.util.List;

public interface NoteService {
     Note addNote(Long jobId, String content, String type);
    List<Note> getNotes(Long jobId);
}
