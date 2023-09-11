package com.sherwin.demo2.rest.resources.mappers;

import com.sherwin.demo2.domain.Material;
import com.sherwin.demo2.domain.entity.MaterialEntity;
import com.sherwin.demo2.rest.resources.v1.MaterialRequest;
import com.sherwin.demo2.rest.resources.v1.MaterialResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    Material fromRequestToMaterial(MaterialRequest request);
    MaterialEntity fromMaterialToMaterialEntity(Material material);
    MaterialResponse fromMaterialEntityToMaterialResponse(MaterialEntity entity);
}
