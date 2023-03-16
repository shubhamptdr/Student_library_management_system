package com.student.library.management.dtos.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueBookRequestDto {
    private int bookId;
    private int cardId;
}
