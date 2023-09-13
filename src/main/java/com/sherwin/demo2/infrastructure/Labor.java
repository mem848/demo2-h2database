package com.sherwin.demo2.infrastructure;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Labor {
    @Positive
    private float length;
    @Positive
    private float width;
    @Positive
    private float pricePerSqft;
    @Positive
    private float cost;
}
