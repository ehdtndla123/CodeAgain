package com.note.plannerweb.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private List<NoteReviewCreateRequest> noteReviews = new ArrayList<>();


}