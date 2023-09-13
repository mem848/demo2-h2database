package com.sherwin.demo2.rest.resources.v1;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaborRequest {
        @Positive
        private double length;
        @Positive
        private double width;
        @Positive
        private double pricePerSqft;
}
