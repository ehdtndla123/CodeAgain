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

    private Long sno;

    private List<StudyMemberResponse> studyMembers = new ArrayList<>();

    private List<StudyPlanResponse> studyPlans = new ArrayList<>();

}
