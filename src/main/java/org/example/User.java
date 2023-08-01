package org.example;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data // lombok
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
}
