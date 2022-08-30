package com.note.plannerweb.plan.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "planner",cascade = CascadeType.REMOVE)
    private List<Plan> plans=new ArrayList<>();

    public Planner(List<Plan> plans){
        this.plans=plans;
    }
}
