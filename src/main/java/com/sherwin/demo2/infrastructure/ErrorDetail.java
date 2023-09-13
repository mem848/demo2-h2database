package com.sherwin.demo2.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDetail {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

}
