package com.note.plannerweb.study.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyProblemResponse {
    private Long id;

    private String code;

    private String subject;
}
