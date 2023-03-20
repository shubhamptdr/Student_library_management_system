package com.student.library.management.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String mobNo;

    private String country;

    private int age;

    //bidirectional mapping
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Card card;

}
