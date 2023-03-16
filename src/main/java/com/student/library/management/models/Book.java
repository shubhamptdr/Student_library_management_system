package com.student.library.management.models;

import com.student.library.management.enums.Genre;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private int noOfBookAvailable;

    private boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // Book is child wrt to Author
    @ManyToOne
    @JoinColumn
    private Author author;

    // Book is child wrt to Card
    @ManyToMany
    @JoinTable(name = "Book_Card")
    private List<Card> listOfCards = new ArrayList<>();


}
