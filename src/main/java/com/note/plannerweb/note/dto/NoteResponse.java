package com.note.plannerweb.note.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteResponse {
    private Long id;

    private Long number;

    private String subject;

    private String description;

    private String category;

    private String code;

    private String memo;


    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime targetDate;

    private List<NoteReviewResponse> noteReviews = new ArrayList<>();
}
