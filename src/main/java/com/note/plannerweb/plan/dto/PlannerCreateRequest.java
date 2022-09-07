package com.note.plannerweb.plan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.plan.domain.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlannerCreateRequest {

    private List<PlanCreateRequest> plans=new ArrayList<>();

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime targetDate;


}
