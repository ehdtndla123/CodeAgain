package com.note.plannerweb.plan.dto;

import com.note.plannerweb.plan.domain.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCreateRequest {
    private String category;

    private String content;

    private Boolean completion;

    public Plan toEntity(){
        return Plan.builder()
                .category(category)
                .content(content)
                .completion(completion)
                .build();
    }

}
