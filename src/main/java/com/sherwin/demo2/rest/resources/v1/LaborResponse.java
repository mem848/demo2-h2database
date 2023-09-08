package com.sherwin.demo2.rest.resources.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaborResponse {
    private double id;
    private Date time_stamp;
    private double length;
    private double width;
    private double pricePerSqft;
    private double cost;
}
