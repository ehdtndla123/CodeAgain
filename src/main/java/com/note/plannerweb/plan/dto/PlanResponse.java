package com.note.plannerweb.plan.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
    private Long id;

    private String category;

    private String content;

    private Boolean completion;
}
