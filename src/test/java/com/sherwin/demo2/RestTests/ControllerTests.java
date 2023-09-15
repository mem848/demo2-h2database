package com.sherwin.demo2.RestTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherwin.demo2.domain.entity.LaborEntity;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mvc;

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

    @Test
    //@RunWith(MockitoJUnitRunner.class)
    //could be an issue with how I imported some static methods?
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

}
