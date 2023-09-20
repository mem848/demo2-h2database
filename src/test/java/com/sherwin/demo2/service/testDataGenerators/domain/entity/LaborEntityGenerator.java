package com.sherwin.demo2.service.testDataGenerators.domain.entity;

import com.sherwin.demo2.domain.entity.LaborEntity;

public class LaborEntityGenerator {
    public static LaborEntity getLaborEntity()
    {
        LaborEntity entity = LaborEntity.builder()
                .id(1)
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .cost(420)
                .build();
        return entity;
    }
}
