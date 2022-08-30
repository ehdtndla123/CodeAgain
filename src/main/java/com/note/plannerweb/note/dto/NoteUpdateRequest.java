package com.note.plannerweb.note.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

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

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;
}
