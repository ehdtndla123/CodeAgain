package com.note.plannerweb.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanUpdateRequest {
    private String category;

    private String content;

    private Boolean completion;
}
