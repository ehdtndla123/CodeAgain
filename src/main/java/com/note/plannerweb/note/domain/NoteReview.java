package com.note.plannerweb.note.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.note.plannerweb.plan.domain.Planner;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NoteReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;

    private Boolean repeat_complete;

    @ManyToOne
    @JoinColumn(name="note_id")
    private Note note;

    public void updateReview(LocalDateTime repeat_time,Boolean repeat_complete) {
        this.repeat_time = repeat_time;
        this.repeat_complete = repeat_complete;
    }

    public void setNote(Note note){
        this.note=note;
    }


}
