package com.note.plannerweb.note.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.note.dto.NoteUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;//문제 번호

    @Column(nullable = false)
    private String subject;//문제 제목

    @Column(nullable = false)
    private String description;//문제 설명

    @Column(nullable = false)
    private String category;//오답 유형

    @Column(nullable = false)
    private String code;//코드 내용

    private String memo;//메모

    private String repeat_complete;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime repeat_time;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Note(Long number,String subject,String description,String category,String code,String memo,String repeat_complete,LocalDateTime repeat_time){
        this.number=number;
        this.subject=subject;
        this.description=description;
        this.category=category;
        this.code=code;
        this.memo=memo;
        this.repeat_complete=repeat_complete;
        this.repeat_time=repeat_time;
    }

    public void update(Long number,String subject,String description,String category,String code,String memo,String repeat_complete,LocalDateTime repeat_time){
        this.number=number;
        this.subject=subject;
        this.description=description;
        this.category=category;
        this.code=code;
        this.memo=memo;
        this.repeat_complete=repeat_complete;
        this.repeat_time=repeat_time;
    }

    public void setMember(Member member){
        this.member=member;
    }

}
