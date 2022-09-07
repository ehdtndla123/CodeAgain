package com.note.plannerweb.plan.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="planner_id")
    private Planner planner;

    private String category;

    private String content;

    private Boolean completion;

    public void setPlanner(Planner planner){
        this.planner=planner;
    }

    public void update(String category,String content,Boolean completion){
        this.category=category;
        this.content=content;
        this.completion=completion;
    }

}
