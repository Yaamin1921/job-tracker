package com.jobtracker.service.impl;

import com.jobtracker.dto.NotesDto;
import com.jobtracker.entity.Job;
import com.jobtracker.entity.Note;
import com.jobtracker.exception.JobNotFoundException;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.repository.NoteRepository;
import com.jobtracker.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService
{
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Note addNote(Long jobId, String content, String type) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));

        Note note = new Note();
        note.setContent(content);
        note.setType(type);
        note.setCreatedAt(LocalDateTime.now());
        note.setJob(job);

        return noteRepository.save(note);
    }

    public List<NotesDto> getNotes(Long jobId) {
        var notesList= noteRepository.findByJobId(jobId);
        var notes=notesList.stream().map(note->mapper.map(note, NotesDto.class)).toList();
        return  notes;
    }

}
