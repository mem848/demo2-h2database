package com.sherwin.demo2.service.testDataGenerators.rest.resources.v1;

import com.sherwin.demo2.rest.resources.v1.LaborRequest;

public class LaborRequestGenerator {
    public static LaborRequest getLaborRequest()
    {
        LaborRequest request = LaborRequest.builder()
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .build();
        return request;
    }
}
