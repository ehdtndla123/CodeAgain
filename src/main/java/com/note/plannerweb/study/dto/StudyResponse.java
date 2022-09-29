package com.note.plannerweb.study.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyResponse {
    private Long id;

    private String name;

    private Long SNO;

    private List<StudyMemberResponse> studyMembers = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private String location;

    private Boolean penaltyTF;

    private String penaltyContent;

    private List<StudyProblemResponse> studyProblems = new ArrayList<>();
}
