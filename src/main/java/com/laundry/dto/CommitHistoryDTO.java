package com.laundry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitHistoryDTO {
    private String hash;
    private String author;
    private String email;
    private String date;
    private String message;
}
