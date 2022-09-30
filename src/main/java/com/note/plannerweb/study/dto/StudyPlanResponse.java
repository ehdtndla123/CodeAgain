package com.note.plannerweb.study.dto;

import com.note.plannerweb.study.domain.Study;
import com.note.plannerweb.study.domain.StudyProblem;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyPlanResponse {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private String location;

    private Boolean penaltyTF;

    private String penaltyContent;

    private List<StudyProblemResponse> studyProblems = new ArrayList<>();
}
