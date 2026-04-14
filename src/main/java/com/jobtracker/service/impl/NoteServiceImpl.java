package com.jobtracker.service.impl;

import com.jobtracker.entity.Job;
import com.jobtracker.entity.Note;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.repository.NoteRepository;
import com.jobtracker.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class NoteServiceImpl implements NoteService
{
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Note addNote(Long jobId, String content, String type) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Note note = new Note();
        note.setContent(content);
        note.setType(type);
        note.setCreatedAt(LocalDateTime.now());
        note.setJob(job);

        return noteRepository.save(note);
    }

    public List<Note> getNotes(Long jobId) {
        return noteRepository.findByJobId(jobId);
    }

}
