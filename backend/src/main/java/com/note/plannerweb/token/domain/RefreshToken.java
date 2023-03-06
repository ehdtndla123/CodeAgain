package com.note.plannerweb.token.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "user_id")
    private Long key;

    @Column(nullable = false)
    private String token;

    public RefreshToken updateToken(String token){
        this.token=token;
        return this;
    }

    @Builder
    public RefreshToken(Long key,String token){
        this.key=key;
        this.token=token;
    }
}
