package com.sherwin.demo2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherwin.demo2.domain.entity.LaborEntity;
import com.sherwin.demo2.domain.repository.LaborRepository;
import com.sherwin.demo2.infrastructure.Labor;
import com.sherwin.demo2.rest.resources.mappers.LaborMapper;
import com.sherwin.demo2.rest.resources.mappers.MaterialMapper;
import com.sherwin.demo2.rest.resources.v1.LaborRequest;
import com.sherwin.demo2.rest.resources.v1.LaborResponse;
import com.sherwin.demo2.service.LaborService;
import com.sherwin.demo2.service.MaterialService;
//import org.junit.Before;
//import org.junit.Test;
import com.sherwin.demo2.service.testDataGenerators.domain.entity.LaborEntityGenerator;
import com.sherwin.demo2.service.testDataGenerators.infrastructure.LaborGenerator;
import com.sherwin.demo2.service.testDataGenerators.rest.resources.v1.LaborRequestGenerator;
import com.sherwin.demo2.service.testDataGenerators.rest.resources.v1.LaborResponseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LaborControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private LaborMapper mockLaborMapper;

    @MockBean
    private MaterialMapper mockMaterialMapper;

    @MockBean
    private LaborService mockLaborService;

    @MockBean
    private MaterialService mockMaterialService;

    private ObjectMapper objectMapper;
    @Autowired
    private LaborRepository repository;

    @BeforeEach
    public void setUp()
    {
        this.objectMapper = new ObjectMapper();
    }

    @Test //this test is testing the post_mapping call in the laborController
    public void given_labor_request_return_labor_response() throws Exception
    {
        LaborRequest request = LaborRequestGenerator.getLaborRequest();
        Labor labor = LaborGenerator.getLabor();
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        LaborResponse response = LaborResponseGenerator.getLaborResponse();

        given(mockLaborMapper.fromRequestToLabor(request)).willReturn(labor);
        given(mockLaborService.createLaborEntity(labor)).willReturn(entity);
        given(mockLaborMapper.fromLaborEntityToResponse(entity)).willReturn(response);

        this.mvc.perform(post("/labors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(420));
    }

    @Test //this is testing get_mapping call in laborController
    public void given_id_return_labor() throws Exception
    { //how to test @get mapping in controller
        //first insert real data labor, then try to get laborEntity back
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        given(mockLaborService.getLabor(1)).willReturn(Optional.of(entity));

        this.mvc.perform(get("/labors/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json value back
                        .content(objectMapper.writeValueAsString(entity))) //we expect variable entity in return
                .andExpect(status().isOk()) //expect status 200 aka Okay
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("length").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("width").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(2.5))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(420));
    }

    @Test //this will test delete method in laborController
    public void given_id_delete_labor_from_database() throws Exception
    {
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();

        given(mockLaborService.deleteLabor(1)).willReturn(Optional.of(entity)); //sends back deleted entity

        this.mvc.perform(delete("/labors/delete/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
                .content(objectMapper.writeValueAsString(entity))) //we expect variable entity in return
                .andExpect(status().isOk()) //expect status 200 aka Okay
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("length").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("width").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(2.5))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(420));
    }

    @Test //test returning all labor in table
    public void return_all_labor_in_table() throws Exception
    {
        Iterable<LaborEntity> laborList = new ArrayList<LaborEntity>() {
            {
                add(new LaborEntity(1, new Date(), new Date(), 13, 12, 3.5, 420));
                add(new LaborEntity(2, new Date(), new Date(), 13, 12, 3.5, 420));
                add(new LaborEntity(3, new Date(), new Date(), 13, 12, 3.5, 420));
            }
        };

        given(mockLaborService.getAllLabor()).willReturn(laborList);

        this.mvc.perform(get("/labors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
                        .content(objectMapper.writeValueAsString(laborList)) )//we expect variable entity in return
                .andExpect(status().isOk()); //expect status 200 aka Okay
    }

    @Test
    public void given_id_and_labor_request_update_labor() throws Exception
    {
        Integer id = 1;
        LaborRequest request = LaborRequestGenerator.getLaborRequest();
        Labor labor = LaborGenerator.getLabor();
        LaborEntity entity = LaborEntityGenerator.getLaborEntity();
        LaborResponse response = LaborResponseGenerator.getLaborResponse();

        //take request to POJO
        given(mockLaborMapper.fromRequestToLabor(request)).willReturn(labor);
        //go to service, get back laborEntity
        given(mockLaborService.updateLabor(id, labor)).willReturn(entity);
        //map to response
        given(mockLaborMapper.fromLaborEntityToResponse(entity)).willReturn(response);

        this.mvc.perform(put("/labors/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
                        .content(objectMapper.writeValueAsString(request)))//we send request in body
                  .andExpect(status().isOk())//expect status 200 aka Okay
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("length").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("width").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(2.5))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(420));
    }

//    @Test
//    public void given_id_and_labor_request_update_labor() throws Exception
//    {
//        //for creating original entity
//
//        Integer id = 1;
//        double len = 14;
//        double wid = 12;
//        double pps = 2.5;
//        double cost = 420;
//        Optional<LaborEntity> entity = Optional.ofNullable(LaborEntity.builder()
//                .id(id).length(len).width(wid).pricePerSqft(pps).cost(cost).build());
//
//        LaborRequest request = LaborRequest.builder()
//                        .length(len).width(wid).pricePerSqft(pps).build();;
//        Labor labor = Labor.builder()
//                        .length(len).width(wid).pricePerSqft(pps).build();
//
//        Optional<LaborEntity> response = Optional.ofNullable(LaborEntity.builder()
//                .id(id).length(len).width(wid).pricePerSqft(pps).cost(cost).build());
//
//        given(laborService.getLabor(id)).willReturn(entity); //look for entity
//        given(mockLaborMapper.fromRequestToLabor(request)).willReturn(labor); //take request, turn into POJO
//        given(laborService.updateLabor(entity, labor)).willReturn(response); //do service call to update laborEntity
//
//
//        this.mvc.perform(put("/labors/update/1")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
//                        .content(objectMapper.writeValueAsString(request)))//we send request in body
//                  .andExpect(status().isOk())//expect status 200 aka Okay
//                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("length").value(14))
//                .andExpect(MockMvcResultMatchers.jsonPath("width").value(12))
//                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(2.5))
//                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(420));
//
//    }

}
