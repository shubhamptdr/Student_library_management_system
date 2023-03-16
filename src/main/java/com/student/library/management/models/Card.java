package com.student.library.management.models;


import com.student.library.management.enums.CardStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp //Auto timestamp when entry created
    private Date createdOn;

    @UpdateTimestamp //set time when entry is updated
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    // child class foreign key wrt to primary key of parent class i.e. Student
    @OneToOne
    @JoinColumn
    private Student studentVariableName; // this variable used in parent class


    @ManyToMany(mappedBy = "listOfCards",cascade = CascadeType.ALL) // Card is parent wrt Book
    private List<Book> booksIssued = new ArrayList<>();


    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transactions> listOfTransaction = new ArrayList<>();


}
