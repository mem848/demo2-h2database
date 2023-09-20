package com.sherwin.demo2.rest.resources.v1;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialResponse {
    private double id;
    private Date createdAt;
    private Date updatedAt;
    @Positive //must required a positive amount of paint required
    private double length;
    @Positive //must required a positive amount of paint required
    private double width;
    @Positive //must required a positive amount of paint required
    private double sqftPerGallon;
    @Positive //must required a positive amount of paint required
    private double gallonsRequired;
}
