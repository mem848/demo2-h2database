package com.sherwin.demo2.rest.resources.v1;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaborResponse {
    private int id;
    private Date createdAt;
    @Positive
    private double length;
    @Positive
    private double width;
    @Positive
    private double pricePerSqft;
    @Positive
    private double cost;
}
