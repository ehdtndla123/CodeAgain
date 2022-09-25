package com.note.plannerweb.note.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteReviewResponse {
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;

    private Boolean repeat_complete;
}
