package com.sherwin.demo2.domain;

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
    private int id;
    private float price;
}
