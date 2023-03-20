package com.student.library.management.models;

import com.student.library.management.enums.TransactionStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private int fine;

    private String transactionId;

    @CreationTimestamp
    private Date transactionDate;

    private boolean isIssuedOperation;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Card card;


}
