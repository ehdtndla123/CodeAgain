package com.note.plannerweb.study.dto;

import com.note.plannerweb.study.domain.StudyProblem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyProblemCreate {

    private String code;

    private String subject;

    public StudyProblem toEntity(){
        return StudyProblem.builder()
                .code(code)
                .subject(subject)
                .build();
    }
}
