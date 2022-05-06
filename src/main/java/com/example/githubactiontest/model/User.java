package com.example.githubactiontest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Table(name = "MEMBER") // DB 테이블 이름을 설정합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "mb_id")
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(name = "login_id", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column()
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column()
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column()
    private Long kakaoId;

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = null;
        this.role = role;
        this.kakaoId = null;
    }

    public User(String username, String password, String email, UserRoleEnum role, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = null;
        this.role = role;
        this.kakaoId = kakaoId;
    }

    public User(String nickname, String encodedPassword, String email, UserRoleEnum role, String email1) {
        this.username = email1;
        this.password = encodedPassword;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }
}