package com.jobtracker.event;

import com.jobtracker.entity.Job;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotesEvent implements Serializable {
    private Long noteId;
    private String type;
    private Long jobId;
    private String companyName;
    private String role;
    private LocalDateTime localDateTime;

}
