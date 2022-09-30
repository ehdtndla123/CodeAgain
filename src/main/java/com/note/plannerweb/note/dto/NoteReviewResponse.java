package com.note.plannerweb.note.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteReviewResponse {
    private Long id;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate repeat_time;

    private Boolean repeat_complete;
}
