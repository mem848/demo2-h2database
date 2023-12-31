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
    private double length;
    private double width;
    private double sqftPerGallon;
    private double gallonsRequired;
}
