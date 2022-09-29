package com.note.plannerweb.study.dto;

import com.note.plannerweb.study.domain.StudyProblem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCreate {
    private Long SNO;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private String location;

    private Boolean penaltyTF;

    private String penaltyContent;

    private List<StudyProblemCreate> studyProblems = new ArrayList<>();

}
