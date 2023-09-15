package com.sherwin.demo2.service;

import com.sherwin.demo2.infrastructure.Labor;
import com.sherwin.demo2.domain.entity.LaborEntity;
import com.sherwin.demo2.domain.repository.LaborRepository;
import com.sherwin.demo2.rest.resources.mappers.LaborMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaborService {
    //this class should take Labor object and calculate price for labor
    @Autowired
    private LaborRepository repository;
    @Autowired
    private LaborMapper mapper;

    public LaborEntity setPrice(Labor labor)
    {
        labor.setCost(labor.getLength()* labor.getWidth()*labor.getPricePerSqft());
        LaborEntity entity = mapper.fromLaborToLaborEntity(labor);
        return repository.save(entity);
    }

    public Iterable<LaborEntity> getAllLabor()
    {
        return repository.findAll();
    }

    public Optional<LaborEntity> getLabor(Integer id)
    {
        return repository.findById(id);
    }

    public void deleteLabor(Integer id)
    {
        repository.deleteById(id);
    }
}
