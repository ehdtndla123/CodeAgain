package com.note.plannerweb.plan.dto;

import com.note.plannerweb.plan.domain.Plan;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannerResponse {

    private Long id;

    private List<PlanResponse> plans = new ArrayList<>();
}
