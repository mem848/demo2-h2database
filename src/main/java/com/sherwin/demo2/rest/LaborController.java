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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
    @GetMapping("/{id}")
    public Optional<LaborEntity> getLabor(@PathVariable Integer id)
    {
        return service.getLabor(id);
    }

    @DeleteMapping("/{id}")
    public Optional<LaborEntity> deleteLabor(@PathVariable Integer id)
    {
        return service.deleteLabor(id);
    }

    @GetMapping("")
    public Iterable<LaborEntity> getAllLabor() {return service.getAllLabor();}
//
//    @PutMapping("update/{id}")
//    public Optional<LaborEntity> updateLabor(@PathVariable Integer id, @Valid @RequestBody LaborRequest laborRequest)
//    {
//        //first we check id to see if it's already in the table
//        Optional<LaborEntity> currentLabor = service.getLabor(id);
//        if(currentLabor.isPresent()){
//            Labor updateLabor = mapper.fromRequestToLabor(laborRequest); //create labor object
//            service.updateLabor(currentLabor, updateLabor); //call service methods to update laborEntity
//        }
//        currentLabor.orElseThrow();
//        //look into Optional mapping because ifPresent is always void
//
//       // return mapper.fromOptionalLaborEntityToResponse(currentLabor);
//        return currentLabor;
//
//    }
//
//    @PutMapping("/{id}") //works the same as update (up above, just throws error earlier if trying to update
//    //labor that isn't in the table
//    public LaborResponse updateLabor(@PathVariable Integer id, @Valid @RequestBody LaborRequest laborRequest)
//    {
//        //first we check id to see if it's already in the table
//        LaborEntity currentLabor = service.getLabor(id)
//            .orElseThrow();
//        //throw exception if not in table, can customize later
//
//        //now call service method to update all fields
//        Labor updateLabor = mapper.fromRequestToLabor(laborRequest);
//        service.updateLabor(currentLabor, updateLabor);
//
//        return mapper.fromLaborEntityToResponse(currentLabor); //return currentLabor
//    }

    @PutMapping("/{id}")
    public LaborResponse updateLabor (@PathVariable Integer id, @Valid @RequestBody LaborRequest laborRequest)
    {
        Labor updateLabor = mapper.fromRequestToLabor(laborRequest); //make request into POJO labor
        LaborEntity entity = service.updateLabor(id, updateLabor); //call service
        return mapper.fromLaborEntityToResponse(entity); //return currentLabor
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
