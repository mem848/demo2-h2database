package com.sherwin.demo2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherwin.demo2.domain.entity.LaborEntity;
import com.sherwin.demo2.domain.repository.LaborRepository;
import com.sherwin.demo2.infrastructure.Labor;
import com.sherwin.demo2.rest.resources.mappers.LaborMapper;
import com.sherwin.demo2.rest.resources.v1.LaborRequest;
import com.sherwin.demo2.service.testDataGenerators.domain.entity.LaborEntityGenerator;
import com.sherwin.demo2.service.testDataGenerators.infrastructure.LaborGenerator;
import com.sherwin.demo2.service.testDataGenerators.rest.resources.v1.LaborRequestGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LaborServiceTest {
    //this file is for testing service calls
    //working from ProfileServiceDefault, and ProfileServiceDefaultTest
    @InjectMocks
    LaborService service;
    @Mock
    LaborRepository repository;
    @MockBean
    LaborMapper mapper;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp()
    {
        this.objectMapper = new ObjectMapper();
    }

    @Test //given some labor, save cost of labor via service method
    public void given_Labor_then_return_cost()
    {
        Labor actual  = LaborGenerator.getLaborWithOutCost();
        double expected = 420.0;
        assertEquals(expected, service.setCost(actual));
    }

    @Test
    public void given_an_id_and_Labor_then_update_LaborEntity()
    {
        Labor labor = LaborGenerator.getLabor();
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();

        given(repository.findById(1)).willReturn(Optional.ofNullable(entity));
        given(repository.save(entity)).willReturn(entity);

        LaborEntity actual = service.updateLabor(1, labor);
        LaborEntity expected = LaborEntity.builder()
                .id(1)
                .length(14)
                .width(12)
                .pricePerSqft(2.5)
                .cost(420)
                .build();
        assertEquals(expected, actual);

    }

}
