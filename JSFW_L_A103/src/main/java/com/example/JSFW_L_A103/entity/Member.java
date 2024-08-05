package com.example.JSFW_L_A103.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "Members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"contents"})
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private String description;
    private LocalDate createdDate;
    private LocalTime updateTime;
    private String role;
    //    @OneToMany(mappedBy = "member", orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<Content> contents;
}
