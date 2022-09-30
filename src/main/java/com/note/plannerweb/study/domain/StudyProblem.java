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

    @ManyToOne
    private StudyPlan studyPlan;



    public void setStudyPlan(StudyPlan studyPlan){
        this.studyPlan = studyPlan;
    }
}
