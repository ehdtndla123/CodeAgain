package com.note.plannerweb.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteUpdateRequest {
    private Long id;

    private Long number;

    private String subject;

    private String description;

    private String category;

    private String code;

    private String memo;

    private String repeat_complete;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate repeat_time;
}
