package com.note.plannerweb.study.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Long SNO;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "study", cascade = CascadeType.REMOVE)
    private List<StudyMember> studyMembers = new ArrayList<>();

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private String location;

    private Boolean penaltyTF;

    private String penaltyContent;

    @OneToMany(mappedBy = "study", cascade = CascadeType.REMOVE)
    private List<StudyProblem> studyProblems = new ArrayList<>();

}
