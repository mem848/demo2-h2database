package com.sherwin.demo2.service.testDataGenerators.rest.resources;

import com.sherwin.demo2.rest.resources.v1.LaborResponse;
import org.springframework.web.bind.annotation.PathVariable;

public class LaborResponseGenerator {
    public static LaborResponse getLaborResponse()
    {
        LaborResponse response = LaborResponse.builder()
                .id(1)
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .cost(420)
                .build();
    }
}

