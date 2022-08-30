package com.note.plannerweb.note.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse {
    private Long number;

    private String subject;

    private String description;

    private String category;

    private String code;

    private String memo;

    private String repeat_complete;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;
}
