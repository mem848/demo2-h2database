package com.sherwin.demo2.service.testDataGenerators.infrastructure;

import com.sherwin.demo2.infrastructure.Labor;

public class LaborGenerator {
    public static Labor getLabor()
    {
        Labor labor = Labor.builder()
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .cost(420)
                .build();
        return labor;
    }
    public static Labor getLaborWithOutCost()
    {
        Labor labor = Labor.builder()
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .build();
        return labor;
    }
}
