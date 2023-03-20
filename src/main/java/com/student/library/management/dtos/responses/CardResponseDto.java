package com.student.library.management.dtos.responses;

import com.student.library.management.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {
    private int id;

    private Date createdOn;

    private Date updatedOn;

    private CardStatus cardStatus;

    private String studentName;
}
