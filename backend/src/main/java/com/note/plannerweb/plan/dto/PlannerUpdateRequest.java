package com.note.plannerweb.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlannerUpdateRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

}
