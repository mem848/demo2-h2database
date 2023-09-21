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

    public LaborEntity createLaborEntity(Labor labor)
    {
        setCost(labor); //set cost of labor
        LaborEntity entity = mapToEntity(labor);
        return saveToRepository(entity);
    }

    public double setCost(Labor labor)
    {
        labor.setCost(labor.getLength()* labor.getWidth()*labor.getPricePerSqft());
       // System.out.println(labor.getCost());
        return labor.getCost();
    }
    public LaborEntity mapToEntity (Labor labor)
    {
        return mapper.fromLaborToLaborEntity(labor);
    }
    public LaborEntity saveToRepository(LaborEntity entity)
    {
        return repository.save(entity);
    }


    public Iterable<LaborEntity> getAllLabor()
    {
        return repository.findAll();
    }

    public Optional<LaborEntity> getLabor(Integer id)
    {
        Optional<LaborEntity> entity = repository.findById(id);
        return entity;
    }

    public Optional<LaborEntity> deleteLabor(Integer id)
    {
        Optional<LaborEntity> deletedLabor = repository.findById(id);
        repository.deleteById(id);
        return deletedLabor;
    }
//    public Optional<LaborEntity> updateLabor(Optional<LaborEntity> currentLabor, Labor updateLabor)
//    {
//        //here we update all labor fields
//        //if I use a functional interface, I can only use one abstract method
//        currentLabor.ifPresent(labor -> labor.setLength(updateLabor.getLength()));
//        currentLabor.ifPresent(labor -> labor.setWidth(updateLabor.getWidth()));
//        currentLabor.ifPresent(labor -> labor.setPricePerSqft(updateLabor.getPricePerSqft()));
//        currentLabor.ifPresent(labor
//                -> labor.setCost(updateLabor.getLength()*updateLabor.getWidth()* updateLabor.getPricePerSqft()));
//        currentLabor.ifPresent(labor -> repository.save(labor));
//        return currentLabor;
//    }
//
//    public LaborEntity updateLabor(LaborEntity currentLabor, Labor updateLabor)
//    {
//        currentLabor.setLength(updateLabor.getLength());
//        currentLabor.setWidth(updateLabor.getWidth());
//        currentLabor.setPricePerSqft(updateLabor.getPricePerSqft());
//        currentLabor.setCost(updateLabor.getLength()*updateLabor.getWidth()*updateLabor.getPricePerSqft());
//        return repository.save(currentLabor);
//    }

    public LaborEntity updateLabor(Integer id, Labor updateLabor)
    {
        LaborEntity currentLabor = getLabor(id)
                .orElseThrow(); //so by the time we get here, we know we have LaborEntity for sure
        currentLabor.setLength(updateLabor.getLength());
        currentLabor.setWidth(updateLabor.getWidth());
        currentLabor.setPricePerSqft(updateLabor.getPricePerSqft());
        currentLabor.setCost(updateLabor.getLength()*updateLabor.getWidth()*updateLabor.getPricePerSqft());
        repository.save(currentLabor);
        return currentLabor;
    }
}
