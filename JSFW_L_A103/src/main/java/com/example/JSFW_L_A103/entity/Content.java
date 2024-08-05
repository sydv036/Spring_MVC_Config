package com.example.JSFW_L_A103.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Contents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Content {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(strategy = "uuid2", name = "uuid2")
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String brief;
    @Column(nullable = false)
    private String content;
    private LocalDate createdDate;
    private LocalTime updateTime;
    private Integer sort;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Member member;

}
