package com.note.plannerweb.member.domain;

import com.note.plannerweb.note.domain.Note;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String name;

    @Column(nullable = false,length=30,unique = true)
    private String email;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<Note> notes=new ArrayList<>();

    @Builder
    public Member(String password, String name, String email,List<Note> notes){
        this.password=password;
        this.name=name;
        this.email=email;
        this.notes=notes;
    }
}
