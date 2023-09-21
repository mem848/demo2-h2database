package com.sherwin.demo2.service.testDataGenerators.domain.entity;

import com.sherwin.demo2.domain.entity.LaborEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static Iterable<LaborEntity> getAllLabor()
    {
        Iterable<LaborEntity> actual = new ArrayList<LaborEntity>() {
            {
                add(new LaborEntity(1, new Date(), new Date(), 14, 12, 2.5, 420));
                add(new LaborEntity(2, new Date(), new Date(), 14, 12, 2.5, 420));
                add(new LaborEntity(3, new Date(), new Date(), 14, 12, 2.5, 420));
            }
        };
        return actual;
    }
}
