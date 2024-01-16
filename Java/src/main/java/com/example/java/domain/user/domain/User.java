package com.example.java.domain.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(
            columnDefinition = "BINARY(16)",
            nullable = false
    )
    private UUID id;

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
