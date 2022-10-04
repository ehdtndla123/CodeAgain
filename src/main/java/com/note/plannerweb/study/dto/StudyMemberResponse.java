package com.note.plannerweb.study.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyMemberResponse {
    private Long id;

    private String name;

    private List<StudyProblemResponse> studyProblems = new ArrayList<>();
}
