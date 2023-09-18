package com.sherwin.demo2.rest.resources.v1;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaborResponse {
    private Integer id;
    private Date createdAt;
    private Date updatedAt;
    @Positive
    private double length;
    @Positive
    private double width;
    @Positive
    private double pricePerSqft;
    @Positive
    private double cost;
}
