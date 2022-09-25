package com.note.plannerweb.note.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.note.plannerweb.note.domain.NoteReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NoteReviewCreateRequest {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;

    private Boolean repeat_complete;

    public NoteReview toEntity(){
        return NoteReview.builder()
                .repeat_time(repeat_time)
                .repeat_complete(repeat_complete)
                .build();
    }
}
