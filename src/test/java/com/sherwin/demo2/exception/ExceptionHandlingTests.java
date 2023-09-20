package com.sherwin.demo2.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherwin.demo2.rest.resources.mappers.LaborMapper;
import com.sherwin.demo2.rest.resources.v1.LaborRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionHandlingTests {
    //just wanting to try out exception handling tests
    @Autowired
    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @MockBean
    LaborMapper mapper;

    @BeforeEach
    void setUp()
    {
        this.objectMapper = new ObjectMapper();
    }

    //should I be returning status 200? We get a response back that our params are wrong, but the application is still
    //alive which is maybe okay
    @Test
    public void given_bad_params_then_return_status_400() throws Exception
    {
        double len = 14;
        double wid = 12;
        //purposely not setting pricePerSqft parameter to evoke error
        LaborRequest request = LaborRequest.builder().length(len).width(wid).build();
        this.mvc.perform(post("/labors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect((ResultMatcher) status().isBadRequest());
    }


}
