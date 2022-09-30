package com.note.plannerweb.study.domain;

import com.note.plannerweb.member.domain.Member;
import lombok.*;

import javax.persistence.*;

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

    public void setStudy(Study study) {
        this.study = study;
    }

}

