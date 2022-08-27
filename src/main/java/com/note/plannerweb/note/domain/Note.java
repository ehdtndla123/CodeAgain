package com.note.plannerweb.note.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private String subject;

    private String description;

    private String category;

    private String code;

    private String memo;

    private String repeat;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;

    @Builder
    public Note(int number,String subject,String description,String category,String code,String memo,String repeat,LocalDateTime repeat_time){
        this.number=number;
        this.subject=subject;
        this.description=description;
        this.category=category;
        this.code=code;
        this.memo=memo;
        this.repeat=repeat;
        this.repeat_time=repeat_time;
    }


}
