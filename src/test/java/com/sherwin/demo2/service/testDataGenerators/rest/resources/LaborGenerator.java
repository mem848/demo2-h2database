package com.sherwin.demo2.service.testDataGenerators.rest.resources;

import com.sherwin.demo2.infrastructure.Labor;

public class LaborGenerator {
    public static Labor getLabor()
    {
        Labor labor = Labor.builder()
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .build();
        return labor;
    }
}
