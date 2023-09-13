package com.sherwin.demo2.rest;

import com.sherwin.demo2.infrastructure.Material;
import com.sherwin.demo2.domain.entity.MaterialEntity;
import com.sherwin.demo2.rest.resources.mappers.MaterialMapper;
import com.sherwin.demo2.domain.repository.MaterialRespository;
import com.sherwin.demo2.rest.resources.v1.MaterialRequest;
import com.sherwin.demo2.rest.resources.v1.MaterialResponse;
import com.sherwin.demo2.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("material")
@RestController
@RequiredArgsConstructor
public class MaterialController {
    @Autowired
    private final MaterialRespository materialRepository;
    @Autowired
    private MaterialMapper mapper;
    @Autowired
    private MaterialService service;

    //when we put in some id, we may or may not receive a row of materialTable
    @GetMapping("/{id}")
    public Optional<MaterialEntity> getMaterialTable(@PathVariable int id)
    {
        return materialRepository.findById(id);
    }

    @GetMapping("all")
    public Iterable<MaterialEntity> getAllMaterial(){return materialRepository.findAll();}
    @PostMapping("")
    public MaterialResponse insertMaterial(@Valid @RequestBody MaterialRequest request)
    {
        //request to material
        Material material = mapper.fromRequestToMaterial(request);
        //set cost via service, as in repo, return MaterialEntity
        MaterialEntity entity = service.setGallonsRequired(material);
        //MaterialEntity to response
        return mapper.fromMaterialEntityToMaterialResponse(entity);

    }
}
