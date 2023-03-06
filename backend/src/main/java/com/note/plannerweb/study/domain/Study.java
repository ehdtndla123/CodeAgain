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

    private Long sno;

    private String name;

    @OneToMany(mappedBy = "study", cascade = CascadeType.REMOVE)
    private List<StudyMember> studyMembers = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.REMOVE)
    private List<StudyPlan> studyPlans = new ArrayList<>();

}
