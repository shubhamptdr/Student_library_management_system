package com.Student_library_management_system.Models;

import com.Student_library_management_system.Enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
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
