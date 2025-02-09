package com.example.jpa2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void update(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
