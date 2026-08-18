package com.jobtracker.event;

import com.jobtracker.entity.Job;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotesEvent implements Serializable {
    private UUID eventId;
    private Long noteId;
    private String type;
    private Long jobId;
    private String companyName;
    private String role;
    private LocalDateTime localDateTime;

}
