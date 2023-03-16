package com.student.library.management.dtos.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBookRequestDto {
    private int bookId;
    private int cardId;
}
