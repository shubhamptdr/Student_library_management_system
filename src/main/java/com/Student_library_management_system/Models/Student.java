package com.Student_library_management_system.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
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
    @OneToOne(mappedBy = "studentVariableName",cascade = CascadeType.ALL)
    private Card card;

}
