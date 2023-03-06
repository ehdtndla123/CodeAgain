package com.note.plannerweb.study.domain;

import com.note.plannerweb.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Member member;
    @ManyToOne
    private Study study;

    @OneToMany(mappedBy = "studyMember",cascade = CascadeType.PERSIST)
    private List<StudyProblem> studyProblems = new ArrayList<>();

    public void setStudy(Study study) {
        this.study = study;
    }

}

