package com.note.plannerweb.study.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StudyProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String subject;

    @ManyToOne(cascade = CascadeType.ALL)
    private StudyPlan studyPlan;

    @ManyToOne(cascade = CascadeType.ALL)
    private StudyMember studyMember;

    public void setStudyPlan(StudyPlan studyPlan){
        this.studyPlan = studyPlan;
    }

    public void updateProblem(String code,String subject){
        this.code=code;
        this.subject=subject;
    }
}
