package com.sherwin.demo2.rest.resources.mappers;
import org.mapstruct.Mapper;
import com.sherwin.demo2.infrastructure.Labor;
import com.sherwin.demo2.domain.entity.LaborEntity;
import com.sherwin.demo2.rest.resources.v1.LaborRequest;
import com.sherwin.demo2.rest.resources.v1.LaborResponse;

@Mapper(componentModel = "spring")
public interface LaborMapper {

    Labor fromRequestToLabor(LaborRequest request);
    LaborEntity fromLaborToLaborEntity(Labor labor);
    LaborResponse fromLaborEntityToResponse(LaborEntity entity);
}
