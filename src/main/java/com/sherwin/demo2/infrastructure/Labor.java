package com.sherwin.demo2.infrastructure;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Labor {
    private double length;
    private double width;
    private double pricePerSqft;
    private double cost;
}
