package com.example.java.domain.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    public void update(String accountId, String name, Sex sex) {
        this.accountId = accountId;
        this.name = name;
        this.sex = sex;
    }

    public void passwordUpdate(String password) {
        this.password = password;
    }
}
