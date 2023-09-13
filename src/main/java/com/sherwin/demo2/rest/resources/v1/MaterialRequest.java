package com.sherwin.demo2.rest.resources.v1;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequest {
    @Positive //require a positive length
    private double length;
    @Positive //require a positive width
    private double width;
    @Positive //require a positive amount of paint coverage
    private double sqftPerGallon;
}
