package com.sherwin.demo2.service;

import com.sherwin.demo2.infrastructure.Material;
import com.sherwin.demo2.domain.entity.MaterialEntity;
import com.sherwin.demo2.domain.repository.MaterialRespository;
import com.sherwin.demo2.rest.resources.mappers.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    @Autowired
    private MaterialRespository repository;
    @Autowired
    private MaterialMapper mapper;

    public MaterialEntity setGallonsRequired(Material material)
    {
        material.setGallons_required(material.getLength()* material.getWidth()/material.getSqftPerGallon());
        MaterialEntity entity = mapper.fromMaterialToMaterialEntity(material);
        return repository.save(entity);
    }
}
