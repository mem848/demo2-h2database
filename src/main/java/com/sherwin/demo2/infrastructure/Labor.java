package com.sherwin.demo2.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Labor {
    private float length;
    private float width;
    private float pricePerSqft;
    private float cost;
}
