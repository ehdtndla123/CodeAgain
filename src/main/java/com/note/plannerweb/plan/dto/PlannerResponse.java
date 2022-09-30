package com.note.plannerweb.plan.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannerResponse {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private List<PlanResponse> plans = new ArrayList<>();
}
