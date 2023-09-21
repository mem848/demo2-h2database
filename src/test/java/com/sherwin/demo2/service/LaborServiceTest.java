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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LaborServiceTest {
    //this file is for testing service calls
    //working from ProfileServiceDefault, and ProfileServiceDefaultTest
    @MockBean
    LaborService service;
    @MockBean
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
        given(service.setCost(actual)).willReturn(420.0);
    }
    @Test //map from labor to laborEntity
    public void given_Labor_mapToEntity_then_return_LaborEntity()
    {
        Labor actual  = LaborGenerator.getLabor();
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        given(mapper.fromLaborToLaborEntity(actual)).willReturn(entity);
    }

    @Test
    public void given_LaborEntity_then_save_to_repository_and_return_LaborEntity()
    {
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        given(repository.save(entity)).willReturn(entity);
    }
    @Test
    public void given_a_labor_object_when_createLaborEntity_is_called_then_return_laborEntity()
    {
        given_Labor_then_return_cost();
        given_Labor_mapToEntity_then_return_LaborEntity();
        given_LaborEntity_then_save_to_repository_and_return_LaborEntity();
    }

    @Test
    public void given_getAllLabor_is_called_return_list_of_labor()
    {
        Iterable<LaborEntity> actual = new ArrayList<LaborEntity>() {
            {
                add(new LaborEntity(1, new Date(), new Date(), 14, 12, 2.5, 420));
                add(new LaborEntity(2, new Date(), new Date(), 14, 12, 2.5, 420));
                add(new LaborEntity(3, new Date(), new Date(), 14, 12, 2.5, 420));
            }
        };

        given(repository.findAll()).willReturn(actual);

    }

    @Test
    public void given_an_id_return_LaborEntity_associated()
    {
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        repository.save(entity);
        given(repository.findById(1)).willReturn(Optional.of(entity));
    }

    @Test
    public void given_an_id_and_Labor_then_update_LaborEntity()
    {
        Labor labor = LaborGenerator.getLabor();
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        repository.save(entity);
        given(repository.findById(1)).willReturn(Optional.ofNullable(entity));
        given(repository.save(entity)).willReturn(entity);
    }

    @Test
    public void given_an_id_delete_LaborEntity_associated()
    {
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        repository.save(entity);
        doNothing().when(repository).deleteById(1);
        service.deleteLabor(1);
        verify(repository).deleteById(1);
    }


}
