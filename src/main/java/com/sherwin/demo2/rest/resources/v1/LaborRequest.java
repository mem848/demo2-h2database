package com.sherwin.demo2.rest.resources.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaborRequest {
        private double length;
        private double width;
        private double pricePerSqft;
}
