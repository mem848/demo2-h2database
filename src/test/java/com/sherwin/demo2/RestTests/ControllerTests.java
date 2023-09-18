package com.sherwin.demo2.RestTests;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private LaborRepository repository;

    @MockBean
    private LaborMapper mockLaborMapper;

    @MockBean
    private MaterialMapper materialMapper;

    @MockBean
    private LaborService laborService;

    @MockBean
    private MaterialService materialService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp()
    {
        this.objectMapper = new ObjectMapper();
    }

    @Test //this test is testing the post_mapping call in the laborController
    public void given_labor_request_return_labor_response() throws Exception
    {
        double len = 14;
        double wid = 12;
        double pps = 2.5;
        double cost = 420;
        Date date = new Date();

        LaborRequest request = LaborRequest.builder()
                .length(len).width(wid).pricePerSqft(pps).build(); //this builds the request all params
        Labor labor = Labor.builder()
                .length(len).width(wid).pricePerSqft(pps).build();
        //when we first map from request --> labor we don't set price
        LaborEntity entity = LaborEntity.builder()
                .id(1).createdAt(date).length(len).width(wid).pricePerSqft(pps).cost(cost).build();
        //we set labor price via service, and that price is reflected in laborEntity
        LaborResponse response = LaborResponse.builder()
                .id(1).createdAt(date).length(len).width(wid).pricePerSqft(pps).cost(cost).build();
        //System.out.println("request here "+request);
        given(mockLaborMapper.fromRequestToLabor(request)).willReturn(labor);
        //System.out.println("post 1st mapping "+labor);
        given(laborService.setPrice(labor)).willReturn(entity);
        //System.out.println("post service "+entity);
        given(mockLaborMapper.fromLaborEntityToResponse(entity)).willReturn(response);
        //System.out.println("post response "+response);

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
        Integer id = 1;
        Date createdAt = new Date();
        Date updatedAt = new Date();
        double len = 14;
        double wid = 12;
        double pps = 2.5;
        double cost = 420;

        //since I'm building out the entire entity, I'm just using the constructor, rather than build
        LaborEntity entity = new LaborEntity(id, createdAt, updatedAt, len, wid, pps, cost);
        repository.save(entity);
        System.out.println(entity);

        given(laborService.getLabor(id)).willReturn(Optional.of(entity));

        this.mvc.perform(get("/labors/get/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json value back
                        .content(objectMapper.writeValueAsString(entity))) //we expect variable entity in return
                .andExpect(status().isOk()) //expect status 200 aka Okay
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(id))
                //.andExpect(MockMvcResultMatchers.jsonPath("createdAt").value(createdAt))
                //dates can't be converted to string
                //.andExpect(MockMvcResultMatchers.jsonPath("updatedAt").value(updatedAt))
                .andExpect(MockMvcResultMatchers.jsonPath("length").value(len))
                .andExpect(MockMvcResultMatchers.jsonPath("width").value(wid))
                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(pps))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(cost));
    }

    @Test //this will test delete method in laborController
    public void given_id_delete_labor_from_database() throws Exception
    {
        Integer id = 1;
        Date createdAt = new Date();
        Date updatedAt = new Date();
        double len = 14;
        double wid = 12;
        double pps = 2.5;
        double cost = 420;
        LaborEntity entity = new LaborEntity(id, createdAt, updatedAt, len, wid, pps, cost);
        repository.save(entity);
        System.out.println(entity);

        given(laborService.deleteLabor(id)).willReturn(Optional.of(entity)); //sends back deleted entity

        this.mvc.perform(delete("/labors/delete/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
                .content(objectMapper.writeValueAsString(entity))) //we expect variable entity in return
                .andExpect(status().isOk()) //expect status 200 aka Okay
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("length").value(len))
                .andExpect(MockMvcResultMatchers.jsonPath("width").value(wid))
                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(pps))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(cost));

    }

    @Test //test returning all labor in table
    public void return_all_labor_in_table() throws Exception
    {
        Integer id = 1;
        Date createdAt = new Date();
        Date updatedAt = new Date();
        double len = 14;
        double wid = 12;
        double pps = 2.5;
        double cost = 420;
        LaborEntity entity = new LaborEntity(id, createdAt, updatedAt, len, wid, pps, cost);
        repository.save(entity);

        id = 2;
        createdAt = new Date();
        updatedAt = new Date();
        len = 10;
        wid = 10;
        pps = 5;
        cost = 500;
        LaborEntity entity2 = new LaborEntity(id, createdAt, updatedAt, len, wid, pps, cost);
        repository.save(entity2);

        id = 3;
        createdAt = new Date();
        updatedAt = new Date();
        len = 1;
        wid = 1;
        pps = 1;
        cost = 1;
        LaborEntity entity3 = new LaborEntity(id, createdAt, updatedAt, len, wid, pps, cost);
        repository.save(entity3);

        System.out.println(entity);
        System.out.println(entity2);
        System.out.println(entity3);

        Iterable<LaborEntity> laborList = repository.findAll();

        given(laborService.getAllLabor()).willReturn(laborList);

        this.mvc.perform(get("/labors/all")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
                        .content(objectMapper.writeValueAsString(laborList))) //we expect variable entity in return
                .andExpect(status().isOk()); //expect status 200 aka Okay

    }

    @Test
    public void given_id_and_labor_request_update_labor() throws Exception
    {
        Integer id = 1;
        double len = 14;
        double wid = 12;
        double pps = 2.5;
        double cost = 420;
        LaborEntity entity = LaborEntity.builder()
                .length(len).width(wid).pricePerSqft(pps).cost(cost).build();

        repository.save(entity);

        double len2 = 10;
        double wid2 = 10;
        double pps2 = 5;
        double cost2 = 500;
        Labor labor = Labor.builder()
                        .length(len2).width(wid2).pricePerSqft(pps2).cost(cost2).build();

        System.out.println(entity);
        System.out.println(labor);

        given(laborService.updateLabor(Optional.of(entity), labor)).willReturn(Optional.of(entity));
        System.out.println(entity); //this isn't updating...

        this.mvc.perform(put("/labors/update/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE) //expecting json back
                        .content(objectMapper.writeValueAsString(entity))) //we expect variable entity in return
                .andExpect(status().isOk()) //expect status 200 aka Okay
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("length").value(len))
                .andExpect(MockMvcResultMatchers.jsonPath("width").value(wid))
                .andExpect(MockMvcResultMatchers.jsonPath("pricePerSqft").value(pps))
                .andExpect(MockMvcResultMatchers.jsonPath("cost").value(cost));

    }
}
