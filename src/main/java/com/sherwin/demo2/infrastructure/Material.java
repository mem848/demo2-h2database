package com.sherwin.demo2.infrastructure;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Positive
    private double length;
    @Positive
    private double width;
    @Positive
    private double sqftPerGallon;
    @Positive
    private double gallonsRequired;
}
