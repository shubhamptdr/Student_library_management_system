package com.student.library.management.models;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String  country;
    private double rating;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> booksWritten = new ArrayList<>();

}
