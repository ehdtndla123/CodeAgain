package com.note.plannerweb.note.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NoteReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate repeat_time;

    private Boolean repeat_complete;

    @ManyToOne
    @JoinColumn(name="note_id")
    private Note note;

    public void updateReview(LocalDate repeat_time,Boolean repeat_complete) {
        this.repeat_time = repeat_time;
        this.repeat_complete = repeat_complete;
    }

    public void setNote(Note note){
        this.note=note;
    }


}
