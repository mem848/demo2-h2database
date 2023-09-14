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
/////really need to commit to gitHub!
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
    @GetMapping("/{id}")
    public Optional<LaborEntity> getLabor(@PathVariable Integer id)
    {
        logger.debug("getting job");
        logger.trace("trace here");
        logger.error("logger error");
        logger.warn("logger warning");

        return laborRepository.findById(id);
    }

    @GetMapping("all")
    public Iterable<LaborEntity> getAllLabor()
    {
        return laborRepository.findAll();
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
