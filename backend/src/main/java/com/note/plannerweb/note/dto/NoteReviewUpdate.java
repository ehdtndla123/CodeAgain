package com.note.plannerweb.note.dto;

import com.note.plannerweb.note.domain.NoteReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NoteReviewUpdate {

    private Boolean repeat_complete;

}
