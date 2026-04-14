package com.jobtracker.service;

import com.jobtracker.dto.NotesDto;
import com.jobtracker.entity.Note;

import java.util.List;

public interface NoteService {
     Note addNote(Long jobId, String content, String type);
    List<NotesDto> getNotes(Long jobId);
}
