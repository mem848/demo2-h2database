package com.sherwin.demo2.domain;

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
    private double gallons_required;
}
