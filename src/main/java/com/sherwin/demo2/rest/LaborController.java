package com.sherwin.demo2.rest;

import com.sherwin.demo2.infrastructure.Labor;
import com.sherwin.demo2.domain.entity.LaborEntity;
import com.sherwin.demo2.domain.repository.LaborRepository;
import com.sherwin.demo2.rest.resources.mappers.LaborMapper;
import com.sherwin.demo2.rest.resources.v1.LaborRequest;
import com.sherwin.demo2.rest.resources.v1.LaborResponse;
import com.sherwin.demo2.service.LaborService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RequestMapping("labors")
@RestController
@RequiredArgsConstructor
@Component
public class LaborController {
    @Autowired
    private final LaborRepository laborRepository;
    @Autowired
    private LaborMapper mapper;
    @Autowired
    private LaborService service;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //we're going to pass in path var and load it into id
    @GetMapping("get/{id}")
    public Optional<LaborEntity> getLabor(@PathVariable Integer id)
    {
        return service.getLabor(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteLabor(@PathVariable Integer id)
    {
        service.deleteLabor(id);
    }

    @GetMapping("all")
    public Iterable<LaborEntity> getAllLabor()
    {
        //create service method, that returns all labors here
        return service.getAllLabor();
    }

    @PutMapping("update")
    public LaborEntity updateLabor(@Valid @RequestBody Integer id)
    {//wait what should the parameter be? I need an Id to find the labor, but also need
        //the param that should be updated as well
        //I should
    }

    @ResponseStatus(HttpStatus.CREATED) //message 201, labors put into repo
    @PostMapping("")
    public LaborResponse insertLabor(@Valid @RequestBody LaborRequest request)
    {
        //request to Labor
        Labor labor = mapper.fromRequestToLabor(request);
        //set price, and save entity to repository
        LaborEntity entity = service.setPrice(labor);
        return mapper.fromLaborEntityToResponse(entity);
    }
}
