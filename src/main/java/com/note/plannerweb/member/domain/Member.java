package com.note.plannerweb.member.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.note.plannerweb.note.domain.Note;
import com.note.plannerweb.plan.domain.Plan;
import com.note.plannerweb.plan.domain.Planner;
import com.note.plannerweb.study.domain.StudyMember;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length=30,unique = true)
    private String email;

    @Column(nullable = false,length = 100)
    private String name;

    //멤버 객체의 권한이 담긴 컬렉션 객체를 Member 조회시 EAGER 로 즉시 로딩하지 않는다면, Porxy객체가 담겨서 반환 되므로
    //제대로 ROLE USER 를 확인할 수 없다.
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<Note> notes=new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<Planner> plans=new ArrayList<>();

    @OneToOne(mappedBy = "member",cascade = CascadeType.REMOVE)
    private StudyMember studyMember;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword(){
        return this.password;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.email;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
