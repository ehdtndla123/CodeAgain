package com.note.plannerweb.plan.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planner")
    private Planner planner;

    private String category;

    private String content;

    private Boolean completion;

    @Builder
    public Plan(Planner planner,String category,String content,Boolean completion){
        this.planner=planner;
        this.category=category;
        this.content=content;
        this.completion=completion;
    }

}
