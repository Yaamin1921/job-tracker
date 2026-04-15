package com.jobtracker.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {
    private String type;
    private String content;
}
