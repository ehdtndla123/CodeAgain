package com.note.plannerweb.note.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteCreateRequest {
    private Long number;

    private String subject;

    private String description;

    private String category;

    private String code;

    private String memo;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime targetDate;

    private List<NoteReviewCreateRequest> noteReviews = new ArrayList<>();


}